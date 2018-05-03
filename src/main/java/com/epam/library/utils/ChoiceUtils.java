package com.epam.library.utils;

import com.epam.library.constants.LibraryConstants;
import com.epam.library.domain.*;
import com.epam.library.service.LibraryService;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import java.io.*;
import java.sql.Date;
import java.util.List;

public class ChoiceUtils {

    private ChoiceUtils() {}

    public static void makeChoice() {

        int choice;

        do {
            System.out.println("=========MENU=========");
            System.out.println("1. Book Category.");
            System.out.println("2. Employee Category");
            System.out.println("3. Accounting Category");
            System.out.println("4. Task Category");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit.");
            System.out.println("======================");

            choice = ScannerUtils.inputInt(LibraryConstants.Messages.Menu.MENU_ITEM);

            switch (choice) {

                case 1:
                    makeBookChoice();
                    break;

                case 2:
                    makeEmployeeChoice();
                    break;

                case 3:
                    makeAccountingChoice();
                    break;

                case 4:
                    makeTaskChoice();
                    break;

                case 5: {
                    Velocity.setProperty("resource.loader", "classpath");
                    Velocity.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

                    final List<Employee> employees = AccountingUtils.getAccoutingDataByCondition(1, Integer.MAX_VALUE);

                    SortUtils.sortByCountOfBooks(employees);

                    VelocityContext context = new VelocityContext() {{
                        put("books", LibraryService.getBooks());
                        put("employees", LibraryService.getEmployees());
                        put("accountings", LibraryService.getAccountings());
                        put("takenBooksByEmployees", employees);
                    }};

                    File file = new File(LibraryConstants.Path.REPORT_PATH);

                    Writer writer = null;
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
                        writer.write(VelocityUtils.getString("template/report.html.vm", context));
                        writer.flush();
                        writer.close();
                    } catch (Exception e) {
                        System.out.println("✖ Try to close velocity 'writer'... ✖");
                    } finally {
                        try {
                            writer.close();
                        } catch (Exception e) {
                            System.out.println("✖ Can't close velocity 'writer'! ✖");
                            throw new RuntimeException(e.getMessage(), e);
                        }
                    }
                    System.out.println(LibraryConstants.Messages.Menu.REPORT_GENERATED);
                }
                    break;

                case 6:
                    System.out.println(LibraryConstants.Messages.Menu.EXIT);
                    break;
            }


        } while(choice != 6);
    }

    private static void makeBookChoice() {

        int choice;

        do {
            System.out.println("======BOOK_MENU======");
            System.out.println("1. Create Book.");
            System.out.println("2. Read Book by 'id'.");
            System.out.println("3. Read all Books.");
            System.out.println("4. Update Book.");
            System.out.println("5. Delete Book by 'id'.");
            System.out.println("6. Exit from 'Book' menu.");
            System.out.println("======================");

            choice = ScannerUtils.inputInt(LibraryConstants.Messages.Menu.MENU_ITEM);

            switch (choice) {

                case 1: {
                    Book book = new Book(
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Book.TITLE),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Book.BRIEF),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Book.AUTHOR),
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Book.YEAR)
                    );
                    LibraryService.addEntity(book);
                }
                    break;

                case 2: {
                    Book book = (Book) LibraryService.getBookById(ScannerUtils.inputInt(LibraryConstants.Messages.Input.Book.ID));
                    System.out.println(book.toString());
                }
                    break;

                case 3: {
                    List<Entity> books = LibraryService.getBooks();
                    for (int i = 0; i < books.size(); i++) {
                        Book book = (Book) books.get(i);
                        System.out.println(book.toString());
                    }
                }
                    break;

                case 4: {
                    Book book = new Book(
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Book.ID),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Book.TITLE),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Book.BRIEF),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Book.AUTHOR),
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Book.YEAR)
                    );
                    LibraryService.updateBook(book);
                }
                    break;

                case 5: {
                    LibraryService.deleteBookById(ScannerUtils.inputInt(LibraryConstants.Messages.Input.Book.ID));
                }
                    break;

                case 7:
                    System.out.println(LibraryConstants.Messages.Util.SUB_EXIT);
                    break;

            }
        } while(choice != 6);
    }

    private static void makeEmployeeChoice() {
        int choice;

        do {
            System.out.println("====EMPLOYEE_MENU====");
            System.out.println("1. Create Employee.");
            System.out.println("2. Read Employee by 'id'.");
            System.out.println("3. Read all Employees.");
            System.out.println("4. Update Employee.");
            System.out.println("5. Delete Employee by 'id'.");
            System.out.println("6. Exit from 'Employee' menu.");
            System.out.println("======================");

            choice = ScannerUtils.inputInt(LibraryConstants.Messages.Menu.MENU_ITEM);

            switch (choice) {

                case 1: {
                    Employee employee = new Employee(
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Employee.NAME),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Employee.EMAIL),
                            Date.valueOf(ScannerUtils.inputString(LibraryConstants.Messages.Input.Employee.DATE_OF_BIRTH))
                    );

                    LibraryService.addEntity(employee);
                }
                break;

                case 2: {
                    Employee employee = (Employee) LibraryService.getEmployeeById(ScannerUtils.inputInt(LibraryConstants.Messages.Input.Employee.ID));
                    System.out.println(employee.toString());
                }
                break;

                case 3: {
                    List<Entity> employees = LibraryService.getEmployees();
                    for (int i = 0; i < employees.size(); i++) {
                        Employee employee = (Employee) employees.get(i);
                        System.out.println(employee.toString());
                    };
                }
                break;

                case 4: {
                    Employee employee = new Employee(
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Employee.ID),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Employee.NAME),
                            ScannerUtils.inputString(LibraryConstants.Messages.Input.Employee.EMAIL),
                            Date.valueOf(ScannerUtils.inputString(LibraryConstants.Messages.Input.Employee.DATE_OF_BIRTH))
                    );

                    LibraryService.updateEmployee(employee);
                }
                break;

                case 5: {
                    LibraryService.deleteEmployeeById(ScannerUtils.inputInt(LibraryConstants.Messages.Input.Employee.ID));
                }
                break;

                case 6:
                    System.out.println(LibraryConstants.Messages.Util.SUB_EXIT);
                    break;

            }
        } while(choice != 6);
    }

    private static void makeAccountingChoice(){
        int choice;

        do {
            System.out.println("===ACCOUNTING_MENU===");
            System.out.println("1. Create Accounting.");
            System.out.println("2. Read Accounting by 'id'.");
            System.out.println("3. Read all Accountings.");
            System.out.println("4. Update Accounting.");
            System.out.println("5. Delete Accounting by 'id'.");
            System.out.println("6. Exit from 'Accounting' menu.");
            System.out.println("======================");

            choice = ScannerUtils.inputInt(LibraryConstants.Messages.Menu.MENU_ITEM);

            switch (choice) {

                case 1: {
                    Accounting accounting = new Accounting(
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.BOOK_ID),
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.EMPLOYEE_ID)
                    );

                    LibraryService.addEntity(accounting);
                }
                    break;

                case 2: {
                    Accounting accounting = (Accounting) LibraryService.getAccountingById(ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.ID));
                    System.out.println(accounting.toString());
                }
                    break;

                case 3: {
                    List<Entity> accountings = LibraryService.getAccountings();
                    for (int i = 0; i < accountings.size(); i++) {
                        Accounting accounting = (Accounting) accountings.get(i);
                        System.out.println(accounting.toString());
                    }
                }
                    break;

                case 4: {
                    Accounting accounting = new Accounting(
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.ID),
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.BOOK_ID),
                            ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.EMPLOYEE_ID)
                    );

                    LibraryService.updateAccounting(accounting);
                }
                    break;

                case 5: {
                    LibraryService.deleteAccountingById(ScannerUtils.inputInt(LibraryConstants.Messages.Input.Accounting.ID));
                }
                    break;

                case 6:
                    System.out.println(LibraryConstants.Messages.Util.SUB_EXIT);
                    break;

            }
        } while(choice != 6);
    }

    private static void makeTaskChoice() {
        int choice;

        do {
            System.out.println("======TASK_MENU======");
            System.out.println("1. Show First task.");
            System.out.println("2. Show Second task.");
            System.out.println("3. Exit from 'Task' menu.");
            System.out.println("======================");

            choice = ScannerUtils.inputInt(LibraryConstants.Messages.Menu.MENU_ITEM);

            switch (choice) {

                case 1: {
                    List<Employee> employees = AccountingUtils.getAccoutingDataByCondition(2, Integer.MAX_VALUE);

                    SortUtils.sortByCountOfBooks(employees);

                    for (Employee employee : employees) {
                        System.out.println("★ " + employee.getName() + ", taken '" + employee.getRentedBooks().size() + "' books ★");
                    }
                }
                break;

                case 2: {
                    List<Employee> employees = AccountingUtils.getAccoutingDataByCondition(0, 6);

                    SortUtils.sortByBirthDate(employees);

                    for (Employee employee : employees) {
                        System.out.println("★ " + employee.getName() + ", " + employee.getDateOfBirth() + ", taken '" + employee.getRentedBooks().size() + "' books ★");
                    }
                }
                break;

                case 3:
                    System.out.println(LibraryConstants.Messages.Util.SUB_EXIT);
                break;

            }
        } while(choice != 3);
    }

}