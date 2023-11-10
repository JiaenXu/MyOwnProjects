/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.CustomerManagement;

import java.util.ArrayList;

import java.util.Map;
import java.util.Random;


import model.Business.Business;
import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class CustomerDirectory {

    Business business;
    ArrayList<CustomerProfile> customerlist;

    public CustomerDirectory(Business d) {

        business = d;
        customerlist = new ArrayList();

    }

    public CustomerProfile newCustomerProfile(Person p) {

        CustomerProfile sp = new CustomerProfile(p);
        customerlist.add(sp);
        return sp;
    }

    public CustomerProfile findCustomer(String id) {

        for (CustomerProfile sp : customerlist) {

            if (sp.isMatch(id)) {
                return sp;
            }
        }
        return null; // not found after going through the whole list
    }

    public CustomersReport generatCustomerPerformanceReport() {
        CustomersReport customersreport = new CustomersReport();

        for (CustomerProfile cp : customerlist) {

            CustomerSummary cs = new CustomerSummary(cp);
            customersreport.addCustomerSummary(cs);
        }
        return customersreport;
    }

    public CustomerProfile pickRandomCustomer() {
        if (customerlist.size() == 0)
            return null;
        Random r = new Random();
        int randomIndex = r.nextInt(customerlist.size());
        return customerlist.get(randomIndex);
    }

    public ArrayList<CustomerProfile> getCustomerList() {
        return customerlist;
    }

    public CustomerProfile FindMostSpentCustomer() {
        CustomerProfile mostSpentCustomer = customerlist.get(0);

        for (CustomerProfile cp : customerlist) {
            if (cp.getPriceSum() > mostSpentCustomer.getPriceSum())
                mostSpentCustomer = cp;
        }

        return mostSpentCustomer;
    }

    // public SupplierDirectory FindMostSalesSupplier() {
    //     SupplierDirectory mostSalesSupplier = business.getSuppliers();

    //     for (SupplierDirectory sd : business.getSuppliers()) {
    //         if (sd.getNumberOfOrders() > mostSalesSupplier.getNumberOfOrders())
    //             mostSalesSupplier = sd;
    //     }

    //     return mostSalesSupplier;
    // }

    

    public void printShortInfo() {
        System.out.println();
        System.out.println("Checking what's inside the Customer directory.");
        System.out.println("There are " + customerlist.size() + " customers in total.");
        System.out.println();
    }
}
