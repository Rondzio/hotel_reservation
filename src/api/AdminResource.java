package api;

import model.Customer;
import model.IRoom;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;
    private ReservationService reservationService;

    private AdminResource() {
        reservationService = ReservationService.getInstance();
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

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room: rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getRooms().values();
    }

    public Collection<Customer> getAllCustomers() {
        //TODO

        return null;
    }

    public void displayAllReservations() {
        //TODO
    }
}
