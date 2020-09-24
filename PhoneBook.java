package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID = 1L;

    static ArrayList<Contact> phoneBook1;

    public PhoneBook() throws IOException, ClassNotFoundException {
        File file = new File("save1.ser");
        if (file.exists()) {
            FileInputStream fis = new FileInputStream("save1.ser");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            phoneBook1 = (ArrayList<Contact>) ois.readObject();
            ois.close();
        } else {
            phoneBook1 = new ArrayList<>();
        }
    }

    public void addContact(Contact contact) {
        phoneBook1.add(contact);
    }

    public void removeContact(int recordNumber) {
        phoneBook1.remove(recordNumber);
    }

    public Contact getContact(int recordNumber) {
        return phoneBook1.get(recordNumber);
    }

    public int getSize() {
        return phoneBook1.size();
    }

    public List<Contact> getPhoneBook() {
        return new ArrayList<>(phoneBook1) ;
    }
}