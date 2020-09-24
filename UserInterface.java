package contacts;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Actions actions;
    private Scanner scanner;

    public UserInterface() throws IOException, ClassNotFoundException {
        actions = new Actions();
        scanner = new Scanner(System.in);
    }

    public void startMenu() throws IOException {
        while (true) {
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");


            String action = scanner.nextLine();
            switch (action) {
                case "add":
                    addMenu();
                    break;
                case "count":
                    actions.count();
                    break;
                case "list":
                    actions.showInfo();
                    break;
                case "search":
                    actions.search();
                    break;
                case "exit":
                    FileOutputStream outputStream = new FileOutputStream("save1.ser");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(PhoneBook.phoneBook1);
                    objectOutputStream.close();
                    scanner.close();
                    actions.closeScanner();
                    return;
                default:
                    System.out.println("Incorrect action.");
            }
        }
    }

    private void addMenu() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase();
        switch (type) {
            case "person":
                actions.addPerson();
                break;
            case "organization":
                actions.addOrganization();
                break;
            default:
                System.out.println("Incorrect type.");
                return;
        }
        System.out.println("The record added.");
    }

}