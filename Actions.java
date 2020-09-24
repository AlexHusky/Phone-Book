package contacts;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Actions {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public Actions() throws IOException, ClassNotFoundException {

        phoneBook = new PhoneBook();
        scanner = new Scanner(System.in);
    }

    public void addPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        LocalDate birthDate = setBirthDate(scanner.nextLine());

        System.out.print("Enter the gender (M, F): ");
        String gender = setGender(scanner.nextLine());

        System.out.print("Enter the number: ");
        String number = setPhoneNumber(scanner.nextLine());


        phoneBook.addContact(
                new Person(
                        name,
                        surname,
                        birthDate,
                        gender,
                        number
                ));
    }

    public void addOrganization() {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the number: ");
        String number = setPhoneNumber(scanner.nextLine());

        phoneBook.addContact(
                new Organization(
                        name,
                        address,
                        number
                ));
    }

    public void remove() {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to remove!");
            return;
        }
        int ind = 0;
        for (Contact contact : phoneBook.getPhoneBook()) {
            System.out.printf("%d. %s\n", ++ind, contact.getFullName());
        }
        System.out.print("Enter index to remove: ");
        int index = isCorrectIndex(scanner.nextLine());
        if (index == -1) {
            System.out.println("Wrong index!");
        } else {
            phoneBook.removeContact(index - 1);
            System.out.println("The record removed.");
        }
    }

    public void edit(String searchAction) {

        Contact contact = phoneBook.getContact(Integer.parseInt(searchAction) - 1);
        contact.editc(contact);
    }

    public void count() {
        System.out.printf("The phone book has %s records.\n", phoneBook.getSize());
    }

    public void closeScanner() {
        scanner.close();
    }

    void showInfo() {
        if (phoneBook.getSize() == 0) {
            System.out.println("Phone book is empty.");
            return;
        }
        int index = 0;
        for (Contact contact : phoneBook.getPhoneBook()) {
            System.out.printf("%d. %s\n", ++index, contact.getFullName());
        }
        System.out.println("\n[list] Enter action ([number], back): ");
        String infoAction = scanner.nextLine().toLowerCase();
        switch(infoAction) {
            case "back":
                return;
            default:
                if (infoAction.matches("\\d+")) {
                    System.out.println(phoneBook.getContact(Integer.parseInt(infoAction) - 1));
                    EDM(infoAction);
                } else {
                    System.out.println("Wrong action!");
                }
                break;
        }


    }

    static LocalDate setBirthDate(String birthDate) {
        boolean validBirthDate = Validator.isValidBirthDate(birthDate);
        if (!validBirthDate) {
            System.out.println("Bad birth date!");
        }
        return validBirthDate ? LocalDate.parse(birthDate) : null;
    }

    static String setGender(String gender) {
        boolean validGender = Validator.isValidGender(gender);
        if (!validGender) {
            System.out.println("Bad gender!");
        }
        return validGender ? gender : "[no data]";
    }

    static String setPhoneNumber(String number) {
        boolean validNumber = Validator.isValidPhoneNumber(number);
        if (!validNumber) {
            System.out.println("Wrong number format!");
        }
        return validNumber ? number : "[no data]";
    }

    private int isCorrectIndex(String input) {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            index = -1;
        }
        if (index > phoneBook.getSize()) {
            index = -1;
        }
        return index;
    }

    public void search() {
        System.out.println("\nEnter search query: ");
        String search = scanner.nextLine().toLowerCase();
        Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        int index = 0;
        for (Contact contact : phoneBook.getPhoneBook()) {
            matcher = pattern.matcher(contact.toString());
            if (matcher.find()) {
                System.out.printf("%d. %s\n", ++index, contact.getFullName());
            }
        }
        System.out.println("\n[search] Enter action ([number], back, again): ");
        String searchAction = scanner.nextLine().toLowerCase();
        switch(searchAction) {
            case "again":
                search();
                break;
            case "back":
                return;

            default:
                if (searchAction.matches("\\d+")) {
                    System.out.println(phoneBook.getContact(Integer.parseInt(searchAction) - 1));
                    EDM(searchAction);
                } else {
                    System.out.println("Wrong action!");
                }
                break;
        }

    }

    public void EDM(String searchAction){
        while(true) {
            System.out.println("\n[record] Enter action (edit, delete, menu): ");
            String recordAction = scanner.nextLine().toLowerCase();
            switch (recordAction) {
                case "edit":
                    edit(searchAction);
                    break;
                case "delete":
                    remove();
                    break;
                case "menu":
                    return;
                default:
                    break;
            }
        }

    }
}