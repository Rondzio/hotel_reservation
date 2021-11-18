package api;

public class Menu {
    private String[] menuItems;

    public Menu(String[] menuItems) {
        this.menuItems = menuItems;
    }

    public void displayMenu() {
        for (String item: menuItems) {
            System.out.println(item);
        }
    };
}
