/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import model.Business.Business;
import model.Business.ConfigureABusiness;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.OrderManagement.OrderItem;
import model.Personnel.Person;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductsReport;
import model.SalesManagement.SalesPersonDirectory;
import model.SalesManagement.SalesPersonProfile;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class RangePricingApplication {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here  

    //Business business = ConfigureABusiness.createABusinessAndLoadALotOfData("Amazon", 20, 10, 30, 100, 10);
    Business businessKing = ConfigureABusiness.createABusinessAndLoadALotOfData("Business2", 30, 10, 20, 50, 25,3,10);

    //Part1: create suppliers, productcatalog and customers
    businessKing.printShortInfo();

    //Part2-1: pick 3 random customers and print their sales orders
    businessKing.printSalesOrders(3);

    //Part2-2: pick 3 random suppliers and print their most expensive product
    businessKing.printMostExpensiveProduct(3);

    //Part2-3: find the customer who spends the most
    businessKing.findMostSpentCustomer();

    //Part2-4: find the supplier with the most sales
    businessKing.printSupplierWithMostSales();

    //Part2-5: find the supplier with least sales
    businessKing.printSupplierWithLeastSales();


  }


}
````