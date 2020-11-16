package com.wipro.sales.service;
import com.wipro.sales.bean.*;
import java.util.*;
import com.wipro.sales.util.*;
import com.wipro.sales.dao.*;
import java.sql.*;
import java.util.Date;
public class Administrator {
	 StockDao stob = new StockDao();
	 SalesDao saob = new SalesDao();
	 Connection con = DBUtil.getDBConnection();
	 PreparedStatement ps;
	
		public String insertStock(Product stockobj) 
		{
			String a1="";
			if(stockobj!=null && stockobj.getProductName().length() >=2)
			{
				a1=stob.generateProductID(stockobj.getProductName());
				stockobj.setProductId(a1);
				stob.insertStock(stockobj);
				return a1;
			}
			else
				return "Data entered is not valid for insertion.";
		}
		
		
		public String deleteStock(String productID)
		{
			int check=0;
			check = stob.deleteStock(productID);
			if(check!=0)
				return "deleted";
			else 
				return "The entered record cannot be deleted";
		}
		
		
//		 public String insertSales(Sales salesobj)
//		 {
//			 Product ob= new Product();
//		  int qty=0,check=0,checkUpdate=0;
//		  String salesId;
//		  if(salesobj==null)                                    //condition 1
//		  {
//		   return "Object not valid for Insertion";
//		  }
//		  if(salesobj.getProductId()==null)                      //condition 2
//		  {
//		   return "Unknown product for sales";
//		  }
//		  try
//		  {
//			  PreparedStatement ps=con.prepareStatement("select * from TBL_STOCK where PRODUCT_ID=?");
//			  ps.setString(1, salesobj.getProductId());
//			  ResultSet rs = ps.executeQuery();
//		  if(rs.getInt("QUANTITY_ON_HAND") < salesobj.getQuantitySold()) {
//				return "Not enough stock on hand for sale";
//		  }
//		  }
//		  catch(SQLException e)
//		  {
//			  e.printStackTrace();
//		  }
//		  
//		  //get current date and sales date to check condition 4
//		  Date date= salesobj.getSalesDate();
//		  Date currentDate=new Date(new java.util.Date().getTime());
//		  if(date.compareTo(currentDate)>0)                                 //condition 4
//		  {
//		   return "Invalid Date";
//		  }
//		   
//		  //f all conditions are valid generate sales id
//		  salesId=saob.generateSalesID(salesobj.getSalesDate());
//		  salesobj.setSalesId(salesId);
//		  
//		  if(saob.insertSales(salesobj)>0)
//		  {
//			  return "Error";
//		  }
//		  int check1=0;
//		  check1=stob.updateStock(salesobj.getProductId(), salesobj.getQuantitySold());
//		  if(check1==0)
//		  {
//			  return "Error";
//		  }
//		  else
//			  return "Sales Completed";
//		 
//		 }
//		 		 
		 
		
		public String insertSales(Sales salesobj) {
			Product p1=new Product();
			if(salesobj==null) {
				
				return "Object not valid for insertion";
			}			
			 if(salesobj.getProductId()==null) {
				return "Unknown product for sale";
			}
			 
			 p1 = stob.getStock(salesobj.getProductId());
			 
			 if(p1.getQuantityOnHand() < salesobj.getQuantitySold()) {
				return "Not enough stock on hand for sale";
			}
			 Date date=salesobj.getSalesDate();
			 Date curr=new Date(new java.util.Date().getTime());
			 if(date.compareTo(curr)>0) {
				 return "Invalid date";
			 }
			 String s=saob.generateSalesID(salesobj.getSalesDate());
			 //System.out.println("s");
			 salesobj.setSalesId(s);
			 if(saob.insertSales(salesobj)<0) {
				 return "Error";
			 }
			 stob.updateStock(salesobj.getProductId(), salesobj.getQuantitySold());
			  if(stob.l==0)
			  {
				  return "Error";
			  }
			  else
			  return "Sales Completed";
		}
		 
		 
		 
		 
		 public  ArrayList<SalesReport> getSalesReport() {
			    return saob.getSalesReport();
			    
			  }
//		 public static ArrayList<SalesReport> getSalesReport()
//		 {
//		  return saob.getSalesReport();
//		 }
}


