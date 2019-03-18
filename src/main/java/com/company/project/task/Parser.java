package com.company.project.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Parser {
    private static Logger                  log     = LoggerFactory.getLogger(Parser.class);


    @Async
    public  void sayHello(int name) {
        log.info("数据开始：" + name);
        try {
			Thread.sleep(50000);
			   System.out.println("线程数字"+name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }

}