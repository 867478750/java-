package com.manage.dom;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;

import com.manage.bean.User;

public class JdomRead {

	private String fileName;

	public JdomRead(String fileName) {
		this.fileName = fileName;
	}

	private String db_name = "db0";
	private String db_username = "system";
	private String db_passwd = "123";
	private String db_host = "127.0.0.1";
	private String db_port = "1521";
	
	private List<User> list = new LinkedList<User>();
	
	


	public List<User> getList() {
		return list;
	}



	public void setList(List<User> list) {
		this.list = list;
	}



	public void jdomWriter() {
		// 一：顶级节点
		Element topTree = new Element("topTree");
		Document doc = new Document(topTree);// 将顶级节点（唯一一个）设置进Document
		Comment ttComment = new Comment("这是一个私有的服务配置文件 showphoneinfo");// 创建注释
		topTree.addContent(ttComment);

		// 二：子节点1
		Element subTree1 = new Element("dbconfig");
		Attribute ab1 = new Attribute("db", "oracle10g");// 子节点属性
		subTree1.setAttribute(ab1);

		Element object1 = new Element("db_name");// 子节点信息
		Element object2 = new Element("db_username");
		Element object3 = new Element("db_passwd");
		Element object4 = new Element("db_host");
		Element object5 = new Element("db_port");

		object1.setText(db_name);
		object2.setText(db_username);
		object3.setText(db_passwd);
		object4.setText(db_host);
		object5.setText(db_port);

		topTree.addContent(subTree1);// 与上级节点（顶级节点）关系
		subTree1.addContent(object1);
		subTree1.addContent(object2);
		subTree1.addContent(object3);
		subTree1.addContent(object4);
		subTree1.addContent(object5);

		//用户信息
		Element subTree2 = new Element("Userconfig");
		Attribute ab2= new Attribute("users", "name&passwd");// 子节点属性
		subTree2.setAttribute(ab2);
		topTree.addContent(subTree2);// 与上级节点（顶级节点）关系
		
		
		Element name = new Element("name");
		name.setText("admin");
		Attribute passwd = new Attribute("passwd", "admin");
		name.setAttribute(passwd);
		
		

		
		subTree2.addContent(name);
	


		// 四：将上述内容写入XML
		XMLOutputter out = new XMLOutputter();
		out.setFormat(out.getFormat().setEncoding("GBK"));
		try {
			out.output(doc, new FileOutputStream(new File(fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public void jdomReader() throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document read_doc = builder.build(new File(fileName));
		
		Element root = read_doc.getRootElement(); // 取得根节点
		
		Element e1 = root.getChild("dbconfig");// 取得子节点
		this.db_name = e1.getChildText("db_name");
		this.db_username = e1.getChildText("db_username");
		this.db_passwd = e1.getChildText("db_passwd");
		this.db_host = e1.getChildText("db_host");
		this.db_port = e1.getChildText("db_port");

		Element e2 = root.getChild("Userconfig");// 取得子节点
		List<Element> userlist = e2.getChildren();
		for(Element user:userlist){
			User u = new User();
			u.setName(user.getText());
			u.setPasswd(user.getAttributeValue("passwd"));
			list.add(u);
		}



	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

	public String getDb_username() {
		return db_username;
	}

	public void setDb_username(String db_username) {
		this.db_username = db_username;
	}

	public String getDb_passwd() {
		return db_passwd;
	}

	public void setDb_passwd(String db_passwd) {
		this.db_passwd = db_passwd;
	}

	public String getDb_host() {
		return db_host;
	}

	public void setDb_host(String db_host) {
		this.db_host = db_host;
	}

	public String getDb_port() {
		return db_port;
	}

	public void setDb_port(String db_port) {
		this.db_port = db_port;
	}


}
