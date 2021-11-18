package api;

import model.Customer;
import model.IRoom;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if (adminResource == null) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email) {
        //TODO

        return null;
    }

    public void addRoom(List<IRoom>rooms) {
        //TODO
    }

    public Collection<IRoom> getAllRooms() {
        //TODO

        return null;
    }

    public Collection<Customer> getAllCustomers() {
        //TODO

        return null;
    }

    public void displayAllReservations() {
        //TODO
    }
}
