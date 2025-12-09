package ui;

import java.util.Scanner;

public class EmployeeInputCollector {

    private final Scanner scanner = new Scanner(System.in);

    public int collectEmployeeId() {
        System.out.print("Enter Employee ID: ");
        return scanner.nextInt();
    }

    public String collectNameQuery() {
        System.out.print("Enter name search: ");
        scanner.nextLine();
        return scanner.nextLine();
    }
}
