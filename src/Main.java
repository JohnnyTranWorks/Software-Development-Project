// package com.crashoutcoders.ems;

// import com.crashoutcoders.ems.controllers.AuthController;
// import com.crashoutcoders.ems.controllers.EmployeeController;
// import com.crashoutcoders.ems.models.*;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.Scanner;

// public class Main {
//     private static AuthController authController = new AuthController();
//     private static EmployeeController employeeController = new EmployeeController();
//     private static String currentSessionToken = null;
//     private static Scanner scanner = new Scanner(System.in);

//     public static void main(String[] args) {
//         System.out.println("=== Employee Management System ===");
        
//         while (true) {
//             if (currentSessionToken == null) {
//                 showLoginMenu();
//             } else {
//                 User currentUser = authController.getCurrentUser(currentSessionToken);
//                 if (currentUser.canAccessAdminFeatures()) {
//                     showAdminMenu();
//                 } else {
//                     showEmployeeMenu();
//                 }
//             }
//         }
//     }

//     private static void showLoginMenu() {
//         System.out.println("\n1. Login");
//         System.out.println("2. Exit");
//         System.out.print("Choose option: ");
        
//         int choice = scanner.nextInt();
//         scanner.nextLine(); // consume newline
        
//         switch (choice) {
//             case 1:
//                 login();
//                 break;
//             case 2:
//                 System.out.println("Goodbye!");
//                 System.exit(0);
//                 break;
//             default:
//                 System.out.println("Invalid option");
//         }
//     }

//     private static void login() {
//         System.out.print("Username: ");
//         String username = scanner.nextLine();
//         System.out.print("Password: ");
//         String password = scanner.nextLine();
        
//         currentSessionToken = authController.login(username, password);
        
//         if (currentSessionToken != null) {
//             System.out.println("Login successful!");
//         } else {
//             System.out.println("Invalid credentials!");
//         }
//     }

//     private static void showAdminMenu() {
//         System.out.println("\n=== HR Admin Menu ===");
//         System.out.println("1. Search Employee");
//         System.out.println("2. Add Employee");
//         System.out.println("3. Update Employee");
//         System.out.println("4. Update Salaries by Range");
//         System.out.println("5. Create Pay Statement");
//         System.out.println("6. View Employee Pay History");
//         System.out.println("7. Logout");
//         System.out.print("Choose option: ");
        
//         int choice = scanner.nextInt();
//         scanner.nextLine();
        
//         switch (choice) {
//             case 1: searchEmployee(); break;
//             case 2: addEmployee(); break;
//             case 3: updateEmployee(); break;
//             case 4: updateSalariesByRange(); break;
//             case 5: createPayStatement(); break;
//             case 6: viewPayHistory(); break;
//             case 7: logout(); break;
//             default: System.out.println("Invalid option");
//         }
//     }

//     private static void showEmployeeMenu() {
//         GeneralEmployee emp = (GeneralEmployee) authController.getCurrentUser(currentSessionToken);
        
//         System.out.println("\n=== Employee Menu ===");
//         System.out.println("1. View My Profile");
//         System.out.println("2. View My Pay History");
//         System.out.println("3. Logout");
//         System.out.print("Choose option: ");
        
//         int choice = scanner.nextInt();
//         scanner.nextLine();
        
//         switch (choice) {
//             case 1: viewMyProfile(emp.getEmpId()); break;
//             case 2: viewPayHistory(emp.getEmpId()); break;
//             case 3: logout(); break;
//             default: System.out.println("Invalid option");
//         }
//     }

//     private static void searchEmployee() {
//         System.out.println("\nSearch by: 1.ID  2.Name  3.SSN  4.DOB");
//         System.out.print("Choose: ");
//         int searchType = scanner.nextInt();
//         scanner.nextLine();
        
//         System.out.print("Enter search term: ");
//         String searchTerm = scanner.nextLine();
        
//         String type = "";
//         switch (searchType) {
//             case 1: type = "ID"; break;
//             case 2: type = "NAME"; break;
//             case 3: type = "SSN"; break;
//             case 4: type = "DOB"; break;
//         }
        
//         List<Employee> results = employeeController.searchEmployees(searchTerm, type);
        
//         if (results.isEmpty()) {
//             System.out.println("No employees found.");
//         } else {
//             System.out.println("\n--- Search Results ---");
//             for (Employee emp : results) {
//                 System.out.printf("ID: %d | Name: %s | DOB: %s | SSN: %s\n", 
//                     emp.getEmpId(), emp.getFullName(), emp.getDob(), emp.getMaskedSSN());
//             }
//         }
//     }

//     private static void addEmployee() {
//         System.out.println("\n=== Add New Employee ===");
        
//         System.out.print("Employee ID: ");
//         int empId = scanner.nextInt();
//         scanner.nextLine();
        
//         System.out.print("First Name: ");
//         String firstName = scanner.nextLine();
        
//         System.out.print("Last Name: ");
//         String lastName = scanner.nextLine();
        
//         System.out.print("Date of Birth (YYYY-MM-DD): ");
//         LocalDate dob = LocalDate.parse(scanner.nextLine());
        
//         System.out.print("SSN (XXX-XX-XXXX): ");
//         String ssn = scanner.nextLine();
        
//         System.out.print("Address: ");
//         String address = scanner.nextLine();
        
//         System.out.print("Division: ");
//         String division = scanner.nextLine();
        
//         System.out.print("Job Title: ");
//         String jobTitle = scanner.nextLine();
        
//         System.out.print("Salary: ");
//         double salary = scanner.nextDouble();
//         scanner.nextLine();
        
//         Employee employee = new Employee(empId, firstName, lastName, dob, ssn, 
//                                         address, division, jobTitle, salary, "ACTIVE");
        
//         String result = employeeController.addEmployee(employee);
//         System.out.println(result);
//     }

//     private static void updateEmployee() {
//         System.out.print("Enter Employee ID to update: ");
//         int empId = scanner.nextInt();
//         scanner.nextLine();
        
//         Employee emp = employeeController.getEmployee(empId);
//         if (emp == null) {
//             System.out.println("Employee not found!");
//             return;
//         }
        
//         System.out.println("\nCurrent Info: " + emp.getFullName());
//         System.out.print("New Address (Enter to skip): ");
//         String address = scanner.nextLine();
//         if (!address.isEmpty()) emp.setAddress(address);
        
//         System.out.print("New Division (Enter to skip): ");
//         String division = scanner.nextLine();
//         if (!division.isEmpty()) emp.setDivision(division);
        
//         System.out.print("New Job Title (Enter to skip): ");
//         String jobTitle = scanner.nextLine();
//         if (!jobTitle.isEmpty()) emp.setJobTitle(jobTitle);
        
//         String result = employeeController.updateEmployee(emp);
//         System.out.println(result);
//     }

//     private static void updateSalariesByRange() {
//         System.out.print("Minimum Salary: ");
//         double min = scanner.nextDouble();
        
//         System.out.print("Maximum Salary: ");
//         double max = scanner.nextDouble();
        
//         System.out.print("Increase Percentage: ");
//         double percent = scanner.nextDouble();
//         scanner.nextLine();
        
//         String result = employeeController.updateSalariesByRange(min, max, percent);
//         System.out.println(result);
//     }

//     private static void createPayStatement() {
//         System.out.print("Employee ID: ");
//         int empId = scanner.nextInt();
//         scanner.nextLine();
        
//         System.out.print("Pay Date (YYYY-MM-DD): ");
//         LocalDate payDate = LocalDate.parse(scanner.nextLine());
        
//         System.out.print("Gross Pay: ");
//         double grossPay = scanner.nextDouble();
        
//         System.out.print("Deductions: ");
//         double deductions = scanner.nextDouble();
//         scanner.nextLine();
        
//         PayStatement ps = new PayStatement(0, empId, payDate, grossPay, deductions, 0);
//         String result = employeeController.createPayStatement(ps);
//         System.out.println(result);
//     }

//     private static void viewMyProfile(int empId) {
//         Employee emp = employeeController.getEmployee(empId);
//         if (emp != null) {
//             System.out.println("\n=== My Profile ===");
//             System.out.println("Name: " + emp.getFullName());
//             System.out.println("DOB: " + emp.getDob());
//             System.out.println("SSN: " + emp.getMaskedSSN());
//             System.out.println("Division: " + emp.getDivision());
//             System.out.println("Job Title: " + emp.getJobTitle());
//             System.out.println("Salary: $" + emp.getSalary());
//             System.out.println("Address: " + emp.getAddress());
//         }
//     }

//     private static void viewPayHistory() {
//         System.out.print("Enter Employee ID: ");
//         int empId = scanner.nextInt();
//         scanner.nextLine();
//         viewPayHistory(empId);
//     }

//     private static void viewPayHistory(int empId) {
//         List<PayStatement> history = employeeController.getPayHistory(empId);
        
//         if (history.isEmpty()) {
//             System.out.println("No pay history found.");
//         } else {
//             System.out.println("\n=== Pay History ===");
//             for (PayStatement ps : history) {
//                 System.out.printf("Date: %s | Gross: $%.2f | Deductions: $%.2f | Net: $%.2f\n",
//                     ps.getPayDate(), ps.getGrossPay(), ps.getDeductions(), ps.getNetPay());
//             }
//         }
//     }

//     private static void logout() {
//         authController.logout(currentSessionToken);
//         currentSessionToken = null;
//         System.out.println("Logged out successfully!");
//     }
// }
import ui.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        menu.start();
    }
}
