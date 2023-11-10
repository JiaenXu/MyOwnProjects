/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.OrderManagement;

import java.util.ArrayList;

import model.CustomerManagement.CustomerProfile;
import model.MarketModel.MarketChannelAssignment;
import model.ProductManagement.Product;
import model.SalesManagement.SalesPersonProfile;
import model.Supplier.Supplier;

/**
 *
 * @author kal bugrara
 */
public class Order {

    ArrayList<OrderItem> orderitems;
    CustomerProfile customer;
    SalesPersonProfile salesperson;
    MarketChannelAssignment mca;
    String status;
    Supplier supplier;

    public Order() {
    }

    public Order(CustomerProfile cp) {
        orderitems = new ArrayList();
        customer = cp;
        customer.addCustomerOrder(this); // we link the order to the customer
        salesperson = null;
        status = "in process";
    }

    public Order(CustomerProfile cp, SalesPersonProfile ep) {
        orderitems = new ArrayList();
        customer = cp;
        salesperson = ep;
        customer.addCustomerOrder(this); // we link the order to the customer
        salesperson.addSalesOrder(this);
    }

    public OrderItem newOrderItem(Product p, int actualprice, int q) {
        OrderItem oi = new OrderItem(p, actualprice, q);
        orderitems.add(oi);
        return oi;
    }

    // order total is the sumer of the order item totals
    public int getOrderTotal() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTotal();
        }
        return sum;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderitems;
    }

    public int getOrderPriceSum() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            // sum = sum + oi.calculatePricePerformance(); // positive and negative values
            sum = sum + oi.calculateActualPriceSum();
        }
        return sum;
    }

    public int getOrderPricePerformance() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.calculatePricePerformance(); // positive and negative values
        }
        return sum;
    }

    public int getNumberOfOrderItemsAboveTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            if (oi.isActualAboveTarget() == true) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    // sum all the item targets and compare to the total of the order
    public boolean isOrderAboveTotalTarget() {
        int sum = 0;
        for (OrderItem oi : orderitems) {
            sum = sum + oi.getOrderItemTargetTotal(); // product targets are added
        }
        if (getOrderTotal() > sum) {
            return true;
        } else {
            return false;
        }

    }

    public void printSalesOrder() {
        System.out.println("Order ID: " + customer.hashCode());
        // System.out.println("Salesperson: " + salesperson.getName());
        System.out.println("Order Status: " + status);
        System.out.println("Order Total: " + getOrderTotal());
        System.out.println("Order Price Performance: " + getOrderPricePerformance());
        System.out.println("Number of Order Items Above Target: " + getNumberOfOrderItemsAboveTarget());
        System.out.println("Is Order Above Total Target: " + isOrderAboveTotalTarget());
        System.out.println("Order Items: ");

        for (OrderItem oi : orderitems) {
            oi.printOrderItemInfo();
        }

    }

    public void CancelOrder() {
        status = "Cancelled";
    }

    public void Submit() {
        status = "Submitted";
    }

}
