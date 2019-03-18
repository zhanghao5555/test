package com.company.project.service.impl;

import com.company.project.dao.XueshengMapper;
import com.company.project.model.Xuesheng;
import com.company.project.service.XueshengService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/16.
 */
@Service
@Transactional
public class XueshengServiceImpl extends AbstractService<Xuesheng> implements XueshengService {
    @Resource
    private XueshengMapper xueshengMapper;

}
