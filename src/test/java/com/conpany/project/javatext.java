package com.conpany.project;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class javatext{



	@Test
	public void test () {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void teste(HttpServletResponse reponse) throws IOException {
		
	//创建	File file = new File("E:\\java\\a.txt");
   //   System.out.println("创建成功了吗？"+file.createNewFile());
		
	
//		
//		 File d1 = new File("E:/java");
//	        System.out.println("FHB下的所有目录/文件清单：");
//	        for(File f:d1.listFiles()) {
//	            System.out.println(f.getPath());
//	        }
	        
		File file = new File("E:/java/a.txt");      
//		FileOutputStream fos = new FileOutputStream(file);
//		String str = "46346assfjfi";
//		byte[] bt = str.getBytes(); //创建流
//		fos.write(bt);	//写入流	
//		fos.close();  //关闭流
		
	FileInputStream inp = new  FileInputStream(file);
//	int i = inp.read();
//	while(i!=-1) {
//		System.out.println("读取的字节码为："+i);
//		i = inp.read();
//
//
//	}
	
	OutputStream writer = reponse.getOutputStream();
	
	byte[]  a=new byte[1024];
	int l=0;
	 while ((l = inp.read(a)) != -1) {
		 writer.write(a,0,l);
            }
	
//		
//		FileReader   rea=new FileReader(file);
//		while(rea.read()!=-1) {
//			System.out.println((char)rea.read());
//			
//		}
//		rea.close();
	}

}
