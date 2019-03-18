package com.conpany.project;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import com.company.project.service.XueshengService;

public class text extends Tester {


    @Resource
    private XueshengService xueshengService;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void test1() {
		System.err.println(xueshengService.findAll());
	}

}
