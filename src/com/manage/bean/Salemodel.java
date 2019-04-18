package com.manage.bean;

import java.io.Serializable;

public class Salemodel implements Serializable {
	private static final long serialVersionUID = 1526705009706221747L;
	private String city;
	private String product;
	private String num;
	private String salesman;
	private String remark;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Salemodel() {
	}
}