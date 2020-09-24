package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Person extends Contact implements Serializable {
    private String surname;
    private String gender;
    private LocalDate birthDate;
    private static final long serialVersionUID = 1L;

    transient Scanner scanner = new Scanner(System.in);

    public Person(String name, String surname, LocalDate birthDate, String gender, String phoneNumber) {
        super(name, phoneNumber);
        this.surname = surname;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        setLastEdit(LocalDateTime.now());
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        setLastEdit(LocalDateTime.now());
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        setLastEdit(LocalDateTime.now());
    }

    @Override
    public String toString() {
        String birthDate = getBirthDate() == null ? "[no data]" : getBirthDate().toString();
        return "Name: " + getName() + "\n"
                + "Surname: " + getSurname() + "\n"
                + "Birth date: " + birthDate + "\n"
                + "Gender: " + getGender() + "\n"
                + "Number: " + getPhoneNumber() + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdit();
    }

    @Override
    public String getFullName() {
        return getName() + " " + getSurname();
    }



    void editc(Contact contact) {

        Person person = (Person) contact;

        System.out.print("Select a field (name, surname, birth, gender, number): ");
        Scanner sc = new Scanner(System.in);
        String field = sc.nextLine().toLowerCase();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                person.setName(sc.nextLine());
                break;
            case "surname":
                System.out.print("Enter surname: ");
                person.setSurname(sc.nextLine());
                break;
            case "birth":
                System.out.print("Enter birth date: ");
                person.setBirthDate(Actions.setBirthDate(sc.nextLine()));
                break;
            case "gender":
                System.out.print("Enter gender: ");
                person.setGender(Actions.setGender(sc.nextLine()));
                break;
            case "number":
                System.out.print("Enter number: ");
                person.setPhoneNumber(Actions.setPhoneNumber(sc.nextLine()));
                break;
            default:
                System.out.println("Incorrect field.");
        }
        System.out.println("Saved");
        System.out.println(contact.toString());
    }
}
