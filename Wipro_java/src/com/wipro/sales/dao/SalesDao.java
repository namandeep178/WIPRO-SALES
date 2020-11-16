package com.wipro.sales.dao;
import com.wipro.sales.util.*;
import com.wipro.sales.bean.*;
import java.sql.*;
import java.util.*;

public class SalesDao {
	PreparedStatement ps;
	Connection con;
	
	public int insertSales(Sales sales)
	{
		int t=0;
		con=DBUtil.getDBConnection();
		try
		{
			ps=con.prepareStatement("insert into TBL_SALES values(?,?,?,?,?)");							//query	
			ps.setString(1, sales.getSalesId());														//Setting data in TBL_SALES
			java.sql.Date sqldate=new java.sql.Date(sales.getSalesDate().getTime());
			ps.setDate(2,sqldate);																		//Setting Date 
			ps.setString(3,sales.getProductId());
			ps.setInt(4,sales.getQuantitySold());
			ps.setDouble(5,sales.getSalesPricePerUnit());
			t=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return t;
	}
	
	public String generateSalesID(java.util.Date salesDate)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(salesDate);
		int a = cal.get(Calendar.YEAR);
		int b=a%100;
		con=DBUtil.getDBConnection();
		try
		{
			PreparedStatement ps= con.prepareStatement("SELECT SEQ_SALES_ID.nextval from dual");
			ResultSet rs= ps.executeQuery();
			if(rs.next())
			{
				return ""+b+rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "";
		}
	
	public ArrayList<SalesReport> getSalesReport()
	{
		ArrayList<SalesReport> sr = new ArrayList<SalesReport>();
		Connection con = DBUtil.getDBConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("select * from V_SALES_REPORT");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				SalesReport s1 = new SalesReport();
				s1.setSalesId(rs.getString(1));
				s1.setSalesDate(rs.getDate(2));
				s1.setProductId(rs.getString(3));
				s1.setProductName(rs.getString(4));
				s1.setQuantitySold(rs.getInt(5));
				s1.setProductUnitPrice(rs.getDouble(6));
			    s1.setSalesPricePerUnit(rs.getDouble(7));
			    s1.setProfitAmount(rs.getDouble(8));
			    sr.add(s1);
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return sr;
	}
	
}







