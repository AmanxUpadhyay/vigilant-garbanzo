import java.util.ArrayList;

public class addressBook {
    private ArrayList<contact> contacts;

    // Constructor to initialize the ArrayList of contacts
    public addressBook() {
        this.contacts = new ArrayList<>();
    }

    // Method to set the contacts ArrayList
    public void setContacts(ArrayList<contact> contacts) {
        this.contacts = contacts;
    }

    // Method to add a new contact to the address book
    public void addContact(contact contact) {
        this.contacts.add(contact);
    }

    // Method to edit an existing contact
    public void editContact(contact oldContact, contact newContact) {
        int index = this.contacts.indexOf(oldContact);
        this.contacts.set(index, newContact);
    }

    // Method to delete a contact
    public void deleteContact(contact contact) {
        this.contacts.remove(contact);
    }

    // Method to search for a contact by name
    public contact searchByName(String name) {
        for (contact c : this.contacts) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    // Method to display all the contacts
    public void displayAll() {
        for (contact c : this.contacts) {
            System.out.println(c);
        }
    }

    // Method to get all contacts
    public ArrayList<contact> getContacts() {
        return this.contacts;
    }
}
