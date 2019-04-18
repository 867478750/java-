package com.manage.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class testBase {

	@Test
	public void testIterratorRemove(){
		class ID {
			private String id;
			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}
		}
		List<ID> list = new ArrayList<ID>();
		for(int i=0;i<10;i++){
			ID e =new ID();
			e.setId("123");
			list.add(e);
		}
		ID e =new ID();
		e.setId("");
		list.add(e);
		ID e1 =new ID();
		e.setId("123");
		list.add(e1);
		
		
		System.out.println(list.size());
		for (Iterator<ID> iterrator=list.iterator(); iterrator.hasNext();) {
			ID detail = (ID) iterrator.next();
			if(detail.getId().equals("") || detail.getId()==null)
				iterrator.remove();
		}
		System.out.println(list.size());
	}

	
}
