package com.boot.yhy.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boot.yhy.entity.Role;
import com.boot.yhy.entity.User;
import com.boot.yhy.repository.UserRepository;
import com.boot.yhy.util.ExcelUtil;


@Controller
public class ExcelController {
	@Resource	
	private UserRepository repository;
	
	  @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
	  @ResponseBody
	  public Map<String, Object> exportExcel(HttpServletResponse response) throws IOException {
	    String fileName = new Date().getTime() + "用户列表.xls";
	    //填充projects数据
	    List<User> users = (List<User>) repository.findAll();
	    List<Map<String, Object>> list = ExcelUtil.createExcelRecord(users);
	    String columnNames[] = {"id号", "姓名", "年龄"};//列名
	    String keys[] = {"no", "name", "age"};//map中的key
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    try {
	      ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    byte[] content = os.toByteArray();
	    InputStream is = new ByteArrayInputStream(content);
	    // 设置response参数，可以打开下载页面
	    response.reset();
	    response.setContentType("application/vnd.ms-excel;charset=utf-8");
	    response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes(), "iso-8859-1"));
	    ServletOutputStream out = response.getOutputStream();
	    BufferedInputStream bis = null;
	    BufferedOutputStream bos = null;
	    try {
	      bis = new BufferedInputStream(is);
	      bos = new BufferedOutputStream(out);
	      byte[] buff = new byte[2048];
	      int bytesRead;
	      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	        bos.write(buff, 0, bytesRead);
	      }
	    } catch (final IOException e) {
	      throw e;
	    } finally {
	      if (bis != null)
	        bis.close();
	      if (bos != null)
	        bos.close();
	    }
	    return null;
	  }
}
