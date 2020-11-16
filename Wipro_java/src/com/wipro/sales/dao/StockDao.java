package com.wipro.sales.dao;
import java.sql.*;
import com.wipro.sales.bean.*;
import com.wipro.sales.util.*;

public class StockDao {
	 Connection con = DBUtil.getDBConnection();
	public int rt,l;
	
	public void insertStock(Product sales)
	{
		int t=0;
		try
		{
			
			PreparedStatement ps=con.prepareStatement("insert into TBL_STOCK values(?,?,?,?,?)");
			ps.setString(1, sales.getProductId());
			ps.setString(2, sales.getProductName());
			ps.setInt(3, sales.getQuantityOnHand());
			ps.setDouble(4, sales.getProductUnitPrice());
			ps.setInt(5, sales.getReorderLevel());
			t=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public String generateProductID(String productName)
	 {
		String productId="";
	  try
	  {
	  int nextID_from_seq=0;
	  String s=productName.substring(0,2);
	  PreparedStatement ps=con.prepareStatement("SELECT SEQ_PRODUCT_ID.nextval from dual");
	  ResultSet rs=ps.executeQuery();
	  if(rs.next())
	   nextID_from_seq=rs.getInt(1);
	  
	  //concatenate seq id and last 2 digits of year
	  productId=nextID_from_seq+s;
	  }
	  catch(Exception e)
	  {
	   System.out.print(e);
	  }
	  return productId;
	 }
	
		  
	public void updateStock(String productID,int soldQty) {
		l=0;
		
		try {
			PreparedStatement ps=con.prepareStatement("select QUANTITY_On_Hand from TBL_STOCK where Product_ID=?");
			ps.setString(1, productID);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				 l=rs.getInt(1);
			}
			ps=con.prepareStatement("update TBL_STOCK set QUANTITY_On_Hand= ? where Product_ID=?");
			ps.setInt(1,l-soldQty);
			ps.setString(2, productID);
			l=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Product  getStock(String productID)
	{
		Product ob = new Product();
		try
		{
			PreparedStatement ps=con.prepareStatement("select * from TBL_STOCK where PRODUCT_ID=?");
			ps.setString(1, productID);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
			ob.setProductId(rs.getString("PRODUCT_ID"));
			ob.setProductName(rs.getString("PRODUCT_NAME"));
			ob.setQuantityOnHand(rs.getInt("QUANTITY_ON_HAND"));
			ob.setProductUnitPrice(rs.getDouble("PRODUCT_UNIT_PRICE"));
			ob.setReorderLevel(rs.getInt("REORDER_LEVEL"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ob;
	}
	public int deleteStock(String productID)
	{
		int t=0;
		try
		{
			PreparedStatement ps=con.prepareStatement("delete from TBL_STOCK where PRODUCT_ID=?");
			ps.setString(1, productID);
			t=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return t;
	}
}
