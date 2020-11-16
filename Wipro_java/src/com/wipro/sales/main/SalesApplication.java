package com.wipro.sales.main;
import com.wipro.sales.bean.*;
import com.wipro.sales.dao.*;
import com.wipro.sales.service.*;
import com.wipro.sales.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;

import java.util.*;
public class SalesApplication {

	public static void main(String[] args) throws Exception {
		Scanner sc= new Scanner(System.in);
		Administrator am= new Administrator();
		int a=0;
		while(a!=-1)
		{
			System.out.println("1. Insert Stock\n2. Delete Stock\n3. Insert Sales\n4. View Sales Report\n5. End.");
			a=sc.nextInt();
			switch(a)
			{
			case 1:
			{
				Product ob = new Product();
				System.out.println("Enter Product-Id.");
				ob.setProductId(sc.next());
				System.out.println("Enter Product name.");
				ob.setProductName(sc.next());
				System.out.println("Enter Quantity on hand.");
				ob.setQuantityOnHand(sc.nextInt());
				System.out.println("Enter Product unit price.");
				ob.setProductUnitPrice(sc.nextDouble());
				System.out.println("Enter Reorderlevel.");
				ob.setReorderLevel(sc.nextInt());
				System.out.println(am.insertStock(ob));
				break;
			}
			
			case 2:
			{
				System.out.println("Enter the ProductId you want to delete.");
				String id = sc.next();
				System.out.println(am.deleteStock(id));
				break;
			}
			
			case 3:
			{
				Sales ob = new Sales();
				System.out.println("Enter Product-Id");
				ob.setProductId(sc.next());
				System.out.println("Enter Quantity Sold");
				ob.setQuantitySold(sc.nextInt());
				System.out.println("Enter date in format(dd/mm/yyyy)");
				String d=sc.next();
				try
				{
					Date date =new SimpleDateFormat("dd/MM/yyyy").parse(d);
					ob.setSalesDate(date);
				}
				catch(ParseException e)
				{
					e.printStackTrace();
				}
				
				System.out.println("Enter Sales per unit price");
				ob.setSalesPricePerUnit(sc.nextDouble());
				//Need to insert date also.
				System.out.println(am.insertSales(ob));
				break;
			}
			
			case 4:
			{
				ArrayList<SalesReport> se=am.getSalesReport();
				for(SalesReport sr:se)
				{
					System.out.format("%-5s %-5s %-5s %-5s %-5d %-5.1f %-5.1f %-5.1f\n",sr.getSalesId(),sr.getProductId(),sr.getProductName(),sr.getSalesDate(),sr.getQuantitySold(),sr.getProductUnitPrice(),sr.getSalesPricePerUnit(),sr.getProfitAmount());
				}
				break;
			}
			
			case 5:
			{
				a=-1;
				break;
			}
			}
		}

	}

}
