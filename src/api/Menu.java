package api;

public class Menu {
    private String[] menuItems;

    public Menu(String[] menuItems) {
        this.menuItems = menuItems;
    }

    public void displayMenu() {
        System.out.println("--------------------------------------------------------");
        for (String item: menuItems) {
            System.out.println(item);
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Please select a number for the menu option");
    }
}
