package api;

import com.sun.tools.javac.Main;
import model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.exit;

public class HotelApplication {
    private Scanner scanner;
    private MainMenu mainMenu;
    private AdminMenu adminMenu;
    private HotelResource hotelResource;
    private AdminResource adminResource;

    public HotelApplication() {
        this.scanner = new Scanner(System.in);
        this.mainMenu = new MainMenu();
        this.adminMenu = new AdminMenu();
        this.hotelResource = HotelResource.getInstance();
        this.adminResource = AdminResource.getInstance();
    }

    public void start() {
        Menu menu = this.mainMenu;
        while (true) {
            menu.displayMenu();
            String userInput = scanner.nextLine();
            try {
                if (menu instanceof MainMenu) {
                    switch (Integer.parseInt(userInput)) {
                        case 1: // 1. Find and reserve a room
                            findAndReservere();
                            break;
                        case 2: // 2. See my reservations
                            listReservations();
                            break;
                        case 3: // 3. Create an account
                            createAccount();
                            break;
                        case 4: // 4. Admin
                            menu = this.adminMenu;
                            break;
                        case 5: // 5. Exit
                            exit(0);
                    }

                } else if (menu instanceof AdminMenu) {
                    switch (Integer.parseInt(userInput)) {
                        case 1: // 1. See all customers
                            showAllCustomers(adminResource.getAllCustomers());
                            break;
                        case 2: // 2. See all rooms
                            showAllRooms(adminResource.getAllRooms());
                            break;
                        case 3: // 3. See all reservations
                            adminResource.displayAllReservations();
                            break;
                        case 4: // 4. Add a room
                            adminResource.addRoom(addRooms());
                            break;
                        case 5: // 5. Back to Main Menu
                            menu = this.mainMenu;
                            break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex.getLocalizedMessage());
            }


        }


    }

    private void findAndReservere() {
        Date checkIn;
        Date checkOut;
        boolean proceedWithReservation = true;
        String email;
        System.out.println("Enter email");
        email = scanner.nextLine();
        if (hotelResource.getCustomer(email) == null) {
            System.out.println("No such account!");
            return;
        }

        while (true) {
            System.out.println("Enter check-in date (dd.MM.yyyy)");
            try {
                checkIn = new SimpleDateFormat("dd.MM.yyyy").parse(scanner.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Wrong date format!");
            }
        }

        while (true) {
            System.out.println("Enter check-out date (dd.MM.yyyy)");
            try {
                checkOut = new SimpleDateFormat("dd.MM.yyyy").parse(scanner.nextLine());
                break;
            } catch (ParseException e) {
                System.out.println("Wrong date format!");
            }
        }

        Collection<IRoom> availableRooms = hotelResource.findARoom(checkIn, checkOut);

        if (!availableRooms.isEmpty()) {

            System.out.println("Rooms available from " + (new SimpleDateFormat("dd.MM.yyyy").format(checkIn)) + " to " + (new SimpleDateFormat("dd.MM.yyyy").format(checkOut)));
            for (IRoom room : hotelResource.findARoom(checkIn, checkOut)) {
                System.out.println(room);
            }
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(checkIn);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            checkIn = calendar.getTime();
            calendar.setTime(checkOut);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            checkOut = calendar.getTime();

            availableRooms = hotelResource.findARoom(checkIn, checkOut);
            if (!availableRooms.isEmpty()) {
                System.out.println("There are no available rooms in timerange provided, we are recommending rooms a week later.");
                System.out.println("Rooms available from " + (new SimpleDateFormat("dd.MM.yyyy").format(checkIn)) + " to " + (new SimpleDateFormat("dd.MM.yyyy").format(checkOut)));
                for (IRoom room : hotelResource.findARoom(checkIn, checkOut)) {
                    System.out.println(room);
                }
            } else {
                System.out.println("We are sorry, but there are no available rooms in our hotel.");
                proceedWithReservation = false;
            }
        }

        if (proceedWithReservation) {
            System.out.println("Which room do you want to reserve from " + (new SimpleDateFormat("dd.MM.yyyy").format(checkIn)) + " to " + (new SimpleDateFormat("dd.MM.yyyy").format(checkOut)));
            String roomId = scanner.nextLine();
            hotelResource.bookARoom(email, hotelResource.getRoom(roomId), checkIn, checkOut);
        }
    }


    private void listReservations() {
        String email;
        System.out.println("Enter email");
        email = scanner.nextLine();
        if (hotelResource.getCustomer(email) == null) {
            System.out.println("No such account!");
        } else {
            for (Reservation reservation : hotelResource.getCustomersReservations(email)) {
                System.out.println(reservation);
            }
        }
    }

    private void createAccount() {
        String firstName;
        String lastName;
        String email;
        System.out.println("Enter first name");
        firstName = scanner.nextLine();
        System.out.println("Enter last name");
        lastName = scanner.nextLine();
        System.out.println("Enter email");
        email = scanner.nextLine();
        if (hotelResource.getCustomer(email) == null) {
            hotelResource.createACustomer(email, firstName, lastName);
        } else {
            System.out.println("This email already exists!");
        }
    }

    private void showAllCustomers(Collection<Customer> allCustomers) {
        for (Customer customer : allCustomers) {
            System.out.println(customer);
        }
    }

    private void showAllRooms(Collection<IRoom> allRooms) {
        for (IRoom room : allRooms) {
            System.out.println(room);
        }
    }

    private List<IRoom> addRooms() {
        ArrayList<IRoom> rooms = new ArrayList<>();
        String roomNumber;
        double price;
        byte roomType;
        String answer;
        while (true) {
            System.out.println("Enter room number");
            roomNumber = scanner.nextLine();
            System.out.println("Enter price per night");
            price = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter room type: 1 for single bed, 2 for double bed");
            do {
                roomType = Byte.parseByte(scanner.nextLine());
            } while (roomType != 1 && roomType != 2);
            if (price == 0) {
                rooms.add(new FreeRoom(roomNumber, price, roomType == 1 ? RoomType.SINGLE : RoomType.DOUBLE));
            } else {
                rooms.add(new Room(roomNumber, price, roomType == 1 ? RoomType.SINGLE : RoomType.DOUBLE));
            }
            System.out.println("Would you like to add another room y/n");
            answer = scanner.nextLine();
            if (answer.toLowerCase().equals("n")) {
                break;
            }
        }
        return rooms;
    }


}
