package api;

import static java.lang.System.exit;

public class MainMenu extends Menu {

    public MainMenu() {
        super(new String[]{"1. Find and reserve a room", "2. See my reservations", "3. Create an account", "4. Admin", "5. Exit"});
    }

    @Override
    public void displayMenu() {
        System.out.println("Main Menu");
        super.displayMenu();
    }
}
