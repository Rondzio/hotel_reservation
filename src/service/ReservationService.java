package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private Map<String, IRoom> rooms;
    private Map<Customer, Reservation> reservations;

    private ReservationService() {
        this.rooms = new HashMap<>();
        this.reservations = new HashMap<>();
    }

    public static ReservationService getInstance() {
        if (reservationService == null) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    public void addRoom (IRoom room) {
        this.rooms.put(room.getRoomNumber(),room);
    }

    public IRoom getARoom(String roomId) {
        return this.rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        //TODO

        return null;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        //TODO

        return null;
    }

    public Map<String, IRoom> getRooms() {
        return rooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        //TODO

        return null;
    }

    public void printAllReservation() {
        //TODO
    }
}
