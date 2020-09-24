package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Organization extends Contact implements Serializable {
    private String address;
    private static final long serialVersionUID = 1L;

    transient private Scanner scanner = new Scanner (System.in);

    public Organization(String name, String address, String phoneNumber) {
        super(name, phoneNumber);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        setLastEdit(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Organization name: " + getName() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Number: " + getPhoneNumber() + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdit();
    }

    @Override
    public String getFullName() {
        return getName();
    }

     void editc(Contact contact) {
        Organization organization = (Organization) contact;
        System.out.println("Select a field (name, address, number): ");
        Scanner sc = new Scanner(System.in); //NPE occurred in previous version when Scanner was not reinitialised
        String field = sc.nextLine();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                organization.setName(sc.nextLine());
                break;
            case "address":
                System.out.print("Enter address: ");
                organization.setAddress(sc.nextLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                organization.setPhoneNumber(Actions.setPhoneNumber(sc.nextLine()));
                break;
            default:
                System.out.println("Incorrect field.");
        }
        System.out.println("Saved");
        System.out.println(organization.toString());
    }
}