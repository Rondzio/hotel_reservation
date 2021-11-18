package api;

import com.sun.tools.javac.Main;

import java.util.Scanner;

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
                        case 4: adminResource.addRoom();
                        case 5:
                            menu = this.mainMenu;
                            break;
                    }
                }
            } catch (Exception exception) {

            }


        }


    }


}
