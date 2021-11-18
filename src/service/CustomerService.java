package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService customerService;
    private Map<String, Customer> customers;

    private CustomerService() {
        this.customers = new HashMap<String, Customer>();
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        try {
           this.customers.put(email, new Customer(firstName, lastName, email));
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public Customer getCustomer(String customerEmail) {
        return this.customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers () {
        return this.customers.values();
    }
}
