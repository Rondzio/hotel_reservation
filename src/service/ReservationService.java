package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    private static ReservationService reservationService;
    private Map<String, IRoom> rooms;
    private Map<Customer, Collection<Reservation>> reservations;

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
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);

        reservations.putIfAbsent(customer, new HashSet<>());
        reservations.get(customer).add(reservation);

        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        // result collection
        Collection<IRoom> freeRooms = new HashSet<>();
        // loop through Hotel rooms
        for (IRoom room : this.rooms.values()) {
            // help variable which will be set to false, if room is not available

            boolean roomIsAvailable = true;
            rooms:
            // loop through customers reservations
            for (Collection<Reservation> reservations : this.reservations.values()) {
                for (Reservation reservation : reservations) {
                    // check if we have this room reserved
                    if (reservation.getRoom().equals(room)) {
                        // check if the room reservations dates are overlapping input dates
                        if (reservation.getCheckInDate().before(checkOutDate) && reservation.getCheckOutDate().after(checkInDate)) {
                            // if it is already reserved, there is no point to look for this room
                            roomIsAvailable = false;
                            break rooms;
                        }
                    }
                }
            }
            // if the room is available, let add it to return collection
            if (roomIsAvailable) {
                freeRooms.add(room);
            }
        }
        return freeRooms;
    }

    public Map<String, IRoom> getRooms() {
        return rooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer);
    }

    public void printAllReservation() {
        for (Collection<Reservation> reservations : this.reservations.values()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}
