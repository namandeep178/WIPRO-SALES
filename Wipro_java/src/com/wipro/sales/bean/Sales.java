package com.wipro.sales.bean;
import java.util.*;
public class Sales {
	
	 String salesId;
	 String productId;
	 int quantitySold;
	 double salesPricePerUnit;
	 java.util.Date salesDate;

	
	public java.util.Date getSalesDate() {
		return salesDate;
	}
	public void setSalesDate(java.util.Date salesDate) {
		this.salesDate = salesDate;
	}	
	public   String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public  String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public  int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public  double getSalesPricePerUnit() {
		return salesPricePerUnit;
	}
	public void setSalesPricePerUnit(double salesPricePerUnit) {
		this.salesPricePerUnit = salesPricePerUnit;
	}
	
}
