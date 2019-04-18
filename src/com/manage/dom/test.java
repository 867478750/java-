package com.manage.dom;



import java.util.List;

import org.junit.Test;

import com.manage.bean.User;

public class test {

	@Test
	public void testWrite() {
		JdomRead xmlw = new JdomRead(
				"manage_config.xml");
		xmlw.jdomWriter();
	}

	@Test
	public void testRead() {
		JdomRead xmlr = new JdomRead(
				"manage_config.xml");
		try {
			xmlr.jdomReader();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<User> ulist = xmlr.getList();
		
		for(User u:ulist){
			System.out.println(u.getName()+" "+u.getPasswd());
		}
		

	}

}
