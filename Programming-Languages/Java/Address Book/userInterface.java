import java.util.Scanner;

public class userInterface {
    private addressBook addressBook;

    public userInterface(addressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void displayMenu() {
        System.out.println("Address Book");
        System.out.println("0. Save contact");
        System.out.println("1. Add a new contact");
        System.out.println("2. Edit an existing contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. View all contacts");
        System.out.println("5. Search for a contact");
        System.out.println("6. Exit");
    }

    public void addContact() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the name of the contact: ");
        String name = input.nextLine();
        System.out.println("Enter the phone number of the contact: ");
        String phoneNumber = input.nextLine();
        System.out.println("Enter the email address of the contact: ");
        String emailAddress = input.nextLine();
        System.out.println("Enter the address of the contact: ");
        String address = input.nextLine();

        contact newContact = new contact(name, phoneNumber, emailAddress, address);
        addressBook.addContact(newContact);
        System.out.println("Contact added successfully.");
    }

    // Method to edit an existing contact
    public void editContact() {
        // Code to get user input for the new contact information
        // and the old contact information
        contact oldContact = getContactInfo();
        contact newContact = getContactInfo();

        // Call the editContact method in the addressBook class
        addressBook.editContact(oldContact, newContact);
    }

    private contact getContactInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Enter email address: ");
        String emailAddress = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        return new contact(name, phoneNumber, emailAddress, address);
    }

    public void deleteContact() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of contact to delete: ");
        String name = input.nextLine();
        contact c = addressBook.searchByName(name);
        if (c != null) {
            addressBook.deleteContact(c);
            System.out.println("Contact successfully deleted.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to search for a contact
    public void searchContact() {
        // Code to get user input for the search criteria
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String name = input.nextLine();
        contact result = addressBook.searchByName(name);
        if (result != null) {
            System.out.println("Contact found: ");
            System.out.println(result);
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Method to display all contacts
    public void displayAllContacts() {
        // Code to call the displayAll method in the addressBook class
        addressBook.displayAll();
    }
}
