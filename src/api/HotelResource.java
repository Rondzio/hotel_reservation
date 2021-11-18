package api;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (hotelResource == null) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    public Customer getCustomer(String email) {
        //TODO
        return null;
    }

    public void createACustomer(String email, String firstName, String lastName){
        //TODO
    }

    public IRoom getRoom (String roomNumber) {
        //TODO

        return null;
    }

    public Reservation bookARoom (String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        //TODO

        return null;
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        //TODO

        return null;
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        //TODO
        return null;
    }
}
