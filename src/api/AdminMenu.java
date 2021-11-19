package api;

import static java.lang.System.exit;

public class AdminMenu extends Menu {
    public AdminMenu() {
        super(new String[]{"1. See all customers", "2. See all rooms", "3. See all reservations", "4. Add a room", "5. Back to Main Menu"});
    }

    @Override
    public void displayMenu() {
        System.out.println("Admin Menu");
        super.displayMenu();
    }
}
