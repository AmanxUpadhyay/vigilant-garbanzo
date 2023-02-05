import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class fileHandler {
    private addressBook addressBook;

    public fileHandler(addressBook addressBook) {
        this.addressBook = addressBook;
    }

    // Method to save the contacts to a file
    public void saveContacts() {
        // Code to write the contacts to a file
        try {
            FileOutputStream fos = new FileOutputStream("contacts.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(addressBook.getContacts());
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    // Method to load the contacts from a file
    public void loadContacts() {
        // Code to read the contacts from a file
        try {
            FileInputStream fis = new FileInputStream("contacts.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            ArrayList<contact> contacts = (ArrayList<contact>) ois.readObject();
            addressBook.setContacts(contacts);
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

}
