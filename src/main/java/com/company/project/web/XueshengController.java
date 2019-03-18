package com.company.project.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Xuesheng;
import com.company.project.service.XueshengService;
import com.company.project.task.Parser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 * 
 *
 * @author misszhang
 * @version $Id: XueshengController.java, v 0.1 2019年3月17日 下午10:28:16 misszhang Exp $
 */
@RestController
@RequestMapping("/xuesheng")
public class XueshengController {
    private static Logger                 log = LoggerFactory.getLogger(XueshengController.class);

    @Resource
    private XueshengService               xueshengService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private Parser                        parser;

    @Autowired
    private TaskExecutor                  taskExecutor;

    /**
     * 
     *
     * @param xuesheng
     * @return
     */
    @PostMapping("/add")
    public Result add(Xuesheng xuesheng) {
        xueshengService.save(xuesheng);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        xueshengService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Xuesheng xuesheng) {
        xueshengService.update(xuesheng);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Xuesheng xuesheng = xueshengService.findById(id);
        // String s=JSON.toJSONString(xuesheng);
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();

        opsForHash.put("1", "6", JSON.toJSONString(xuesheng));
        System.out.println(opsForHash.get("1", xuesheng.getId() + ""));

        return ResultGenerator.genSuccessResult(xuesheng);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Xuesheng> list = xueshengService.findAll();
        String[] strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = JSON.toJSONString(list.get(i));
        }

        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.leftPushAll("xuesheng", strings);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @GetMapping("/text")
    public void text(HttpServletResponse response, HttpSession session,
                     HttpServletRequest request) {

        // ListOperations<String, String> opsForList = redisTemplate.opsForList();
        // opsForList.set("xuesheng", 3, "update");

        // 消息发送
        redisTemplate.convertAndSend("mq_02", "发送信息内容01");
        log.info("发送成功");
        session.setAttribute("name", "gg");
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "太好了");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("JSESSIONID")) {
                HttpSession sess = session.getSessionContext().getSession(cookies[i].getValue());
                System.out.println(sess.getAttribute("name"));

            }
            ;

        }

        //   for (int i = 0; i < 30; i++) {
        //	   parser.sayHello(1);
        //   } 		

        //读取本地json
        //		   String json;
        //		try {
        //			  File jsonFile = ResourceUtils.getFile("classpath:china.json");
        //			json = FileUtils.readFileToString(jsonFile,"UTF-8");
        //			JSONArray jsonArray = JSONArray.parseArray(json);
        //			System.out.println(jsonArray.size());
        //			for (int i = 0; i < jsonArray.size(); i++) {
        //				JSONObject jsonObject = jsonArray.getJSONObject(i);
        //				System.out.println(jsonObject.get("name"));
        //			}
        //		} catch (IOException e) {
        //			// TODO Auto-generated catch block
        //			e.printStackTrace();
        //		}

        //线程启动
        //    	taskExecutor.execute(() -> {
        //            log.info("Real thread begin to execute!");

        //            try {
        //                Thread.sleep(5000);
        //            } catch (InterruptedException e) {
        //                log.error("Real thread was interrupted!", e);
        //                return;
        //            }
        //
        //            log.info("Real thread has been executed!");
        //        });

    }
}
