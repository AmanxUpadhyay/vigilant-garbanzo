import java.util.Scanner;
import java.io.File;

public class app {
    public static void main(String[] args) {
        addressBook addressBook = new addressBook();
        userInterface userInterface = new userInterface(addressBook);
        fileHandler fileHandler = new fileHandler(addressBook);

        // Try to load the contacts from the file
        File file = new File("contacts.ser");
        if (file.exists()) {
            try {
                fileHandler.loadContacts();
            } catch (Exception e) {
                System.out.println("Error while loading the contacts: " + e.getMessage());
            }
        }

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 6) {
            userInterface.displayMenu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    userInterface.addContact();
                    break;
                case 2:
                    userInterface.editContact();
                    break;
                case 3:
                    userInterface.deleteContact();
                    break;
                case 4:
                    userInterface.searchContact();
                    break;
                case 5:
                    userInterface.displayAllContacts();
                    break;
                case 6:
                    // Save the contacts to the file
                    fileHandler.saveContacts();
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        sc.close();
    }
}
