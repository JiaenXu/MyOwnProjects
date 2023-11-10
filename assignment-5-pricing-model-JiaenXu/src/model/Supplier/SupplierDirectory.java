/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Supplier;

import java.util.ArrayList;
import java.util.Random;

import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;

/**
 *
 * @author kal bugrara
 */
public class SupplierDirectory {
    ArrayList<Supplier> suppliers;
    // ArrayList<Supplier> pickedSuppliers;
    Product product;

    public SupplierDirectory(){
        suppliers = new ArrayList();
        // pickedSuppliers = new ArrayList();
    }
    public Supplier newSupplier(String n){
        Supplier supplier = new Supplier(n);
        suppliers.add(supplier);
        return supplier;

    }
    public Supplier findSupplier(String id){
        
        for (Supplier supplier: suppliers){
            
            if(supplier.getName().equals(id)) return supplier;
        }
        return null;
        }
    public ArrayList<Supplier> getSuplierList(){
        return suppliers;
    }

    public Supplier pickRandomSupplier(){
        if (suppliers.size() == 0) return null;
        Random r = new Random();
        int randomIndex = r.nextInt(suppliers.size());
        return suppliers.get(randomIndex);
    }

    public Product getMostExpensiveProduct() {
        ProductCatalog catalog = new ProductCatalog();
        ArrayList<Product> products = catalog.getProducts();

        if (products.size() == 0)
            return null;
        
        Product mostExpensiveProduct = products.get(0);

        for (Product p : products) {
            if (p.getCeilingPrice() > mostExpensiveProduct.getCeilingPrice())
                mostExpensiveProduct = p;
        }

        return mostExpensiveProduct;
    }


    public void printShortInfo(){
        System.out.println("Checking what's inside the supplier directory.");
        System.out.println("There are " + suppliers.size() + " suppliers in total.");
        System.out.println();
        // Print all the suppliers, including their product catalogs
        for (Supplier s: suppliers){
            s.printShortInfo();
        }    

        System.out.println();

    }

    
}