package com.akapapaj.java2_week1.mylib;

/*
 *  Author: Joseph Malone akaPapaJ
 *  Class:  Java 2 - Week 1
 *  Instructor: Josh Donlan
 *  Date: November 21, 2012
 */
public class Product {
	public String prod_sku;
	public String prod_name;
	public String prod_desc;
	public String prod_price;
	public String prod_link;
	public Product(String sku, String name, String desc, String price, String link){
		prod_sku = sku;
		prod_name = name;
		prod_desc = desc;
		prod_price = price;
		prod_link = link;
	}
}
