package com.wipro.sales.bean;
public class Product {
	
	
	 String productId;
	 String productName;
	 int quantityOnHand;
	 double productUnitPrice;
	 int reorderLevel;

	public  String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId=productId;
	}
	public  String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantityOnHand() {
		return quantityOnHand;
	}
	public void setQuantityOnHand(int quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}
	public  double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public  int getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(int recordLevel) {
		this.reorderLevel = recordLevel;
	}
	

}
