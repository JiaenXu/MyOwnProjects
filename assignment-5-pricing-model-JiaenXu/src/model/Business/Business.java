/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.MarketCatalog;
import model.MarketingManagement.MarketingPersonDirectory;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.Personnel.EmployeeDirectory;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.ProductSummary;
import model.ProductManagement.ProductsReport;
import model.ProductManagement.SolutionOfferCatalog;
import model.SalesManagement.SalesPersonDirectory;
import model.SalesManagement.SalesPersonProfile;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;
import model.UserAccountManagement.UserAccountDirectory;
import model.CustomerManagement.CustomerProfile;

/**
 *
 * @author kal bugrara
 */
public class Business {

    String name;
    PersonDirectory persondirectory;
    MasterOrderList masterorderlist;
    SupplierDirectory suppliers;
    MarketCatalog marketcatalog;
    ChannelCatalog channelcatalog;
    SolutionOfferCatalog solutionoffercatalog;
    CustomerDirectory customerdirectory;
    EmployeeDirectory employeedirectory;
    SalesPersonDirectory salespersondirectory;
    UserAccountDirectory useraccountdirectory;
    MarketingPersonDirectory marketingpersondirectory;

    public Business(String n) {
        name = n;
        masterorderlist = new MasterOrderList();
        suppliers = new SupplierDirectory();
        persondirectory = new PersonDirectory();
        customerdirectory = new CustomerDirectory(this);
        salespersondirectory = new SalesPersonDirectory(this);
        useraccountdirectory = new UserAccountDirectory();
        marketingpersondirectory = new MarketingPersonDirectory(this);
        employeedirectory = new EmployeeDirectory(this);

    }

    public int getSalesVolume() {
        return masterorderlist.getSalesVolume();

    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public UserAccountDirectory getUserAccountDirectory() {
        return useraccountdirectory;
    }

    public MarketingPersonDirectory getMarketingPersonDirectory() {
        return marketingpersondirectory;
    }

    public SupplierDirectory getSupplierDirectory() {
        return suppliers;
    }

    public ProductsReport getSupplierPerformanceReport(String n) {
        Supplier supplier = suppliers.findSupplier(n);
        if (supplier == null) {
            return null;
        }
        return supplier.prepareProductsReport();

    }

    public ArrayList<ProductSummary> getSupplierProductsAlwaysAboveTarget(String n) {

        ProductsReport productsreport = getSupplierPerformanceReport(n);
        return productsreport.getProductsAlwaysAboveTarget();

    }

    public int getHowManySupplierProductsAlwaysAboveTarget(String n) {
        ProductsReport productsreport = getSupplierPerformanceReport(n); // see above
        int i = productsreport.getProductsAlwaysAboveTarget().size(); // return size of the arraylist
        return i;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerdirectory;
    }

    public SalesPersonDirectory getSalesPersonDirectory() {
        return salespersondirectory;
    }

    public MasterOrderList getMasterOrderList() {
        return masterorderlist;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeedirectory;
    }

    // Part2-1: Pick another three customers, and create salesorders for them
    public ArrayList<CustomerProfile> pickRandomCustomerForSalesOrders(int pickedCustomerCountforSalesOrders) {
        ArrayList<CustomerProfile> pickedCustomers = new ArrayList<>();
        Collections.shuffle(customerdirectory.getCustomerList());

        int counter = 0;
        while (counter < pickedCustomerCountforSalesOrders) {
            CustomerProfile randomcustomer = customerdirectory.pickRandomCustomer();
            if (randomcustomer.getOrders().size() == 0) {
                continue;
            }
            pickedCustomers.add(randomcustomer);
            counter++;
        }
        return pickedCustomers;
    }

    public void printSalesOrders(int pickNum) {
        ArrayList<CustomerProfile> xx = pickRandomCustomerForSalesOrders(pickNum);
        for (CustomerProfile c : xx) {
            c.printSalesOrder();
        }
    }

    // Part2-2: Pick the most expensive product from a random supplier
    public ArrayList<Supplier> pickRandomSupplierForExpensiveProduct(int pickedSpCountforProductPrice) {

        Collections.shuffle(suppliers.getSuplierList());

        ArrayList<Supplier> ps = new ArrayList<>();
        int counter = 0;
        while (counter < pickedSpCountforProductPrice) {
            Supplier randomsupplier = suppliers.pickRandomSupplier();
            ProductCatalog pc = randomsupplier.getProductCatalog();
            if (pc.getProductList().size() == 0) {
                continue;
            }
            ps.add(randomsupplier);
            counter++;
        }

        return ps;
    }

    public void printMostExpensiveProduct(int pickNum) {
        ArrayList<Supplier> xx = pickRandomSupplierForExpensiveProduct(pickNum);
        for (Supplier s : xx) {
            s.printMostExpensiveProduct();
        }
    }

    public void findMostSpentCustomer(){
    CustomerProfile mostSpentCustomer = customerdirectory.FindMostSpentCustomer();
    mostSpentCustomer.printMostSpentCustomer();
    }

    public Supplier findSupplierWithMostSales() {
        Supplier supplierWithMostSales = suppliers.getSuplierList().get(0);

        for (Supplier s : suppliers.getSuplierList()) {
            if (s.getNumberOfOrders() > supplierWithMostSales.getNumberOfOrders())
                supplierWithMostSales = s;
        }

        return supplierWithMostSales;
    }
    
    public Supplier findSupplierWithLeastSales() {
        Supplier supplierWithLeastSales = null;
    
        // Find the first supplier with non-zero orders
        for (Supplier s : suppliers.getSuplierList()) {
            if (s.getNumberOfOrders() != 0) {
                supplierWithLeastSales = s;
                break;
            }
        }
    
        // If all suppliers have zero orders, return null
        if (supplierWithLeastSales == null) {
            return null;
        }
    
        for (Supplier s : suppliers.getSuplierList()) {
            if (s.getNumberOfOrders() != 0 && s.getNumberOfOrders() < supplierWithLeastSales.getNumberOfOrders()) {
                supplierWithLeastSales = s;
            }
        }
    
        return supplierWithLeastSales;
    }

    public void printSupplierWithMostSales() {
        Supplier supplierWithMostSales = findSupplierWithMostSales();
        System.out.println("The supplier with most sales is " + supplierWithMostSales.getName() + " with "
                + supplierWithMostSales.getNumberOfOrders() + " orders.");
    }

    public void printSupplierWithLeastSales() {
        Supplier supplierWithLeastSales = findSupplierWithLeastSales();
        System.out.println("The supplier with least sales is " + supplierWithLeastSales.getName() + " with "
                + supplierWithLeastSales.getNumberOfOrders() + " orders.");
        System.out.println();
    }

    public void printShortInfo() {
        System.out.println("*********************************************************");
        System.out.println("Checking what's inside the business hierarchy.");
        System.out.println();
        suppliers.printShortInfo();
        customerdirectory.printShortInfo();
        masterorderlist.printShortInfo();

    }


}
