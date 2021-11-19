package api;

import com.sun.tools.javac.Main;
import model.FreeRoom;
import model.IRoom;
import model.Room;
import model.RoomType;

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
                        case 1:
                            break;
                        case 4:
                            menu = this.adminMenu;
                            break;
                        case 5:
                            exit(0);
                    }

                } else if (menu instanceof AdminMenu) {
                    switch (Integer.parseInt(userInput)) {
                        case 1:
                            break;
                        case 2:
                            displayAllRooms(adminResource.getAllRooms());
                            break;
                        case 4:
                            adminResource.addRoom(addRooms());
                            break;
                        case 5:
                            menu = this.mainMenu;
                            break;
                    }
                }
            } catch (Exception exception) {

            }


        }


    }

    private void displayAllRooms(Collection<IRoom> allRooms) {
        for (IRoom room: allRooms) {
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
