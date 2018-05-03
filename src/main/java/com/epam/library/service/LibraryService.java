package com.epam.library.service;

import com.epam.library.constants.LibraryConstants;
import com.epam.library.dao.AccountingDAO;
import com.epam.library.dao.BaseDAO;
import com.epam.library.dao.impl.AccountingDAOImpl;
import com.epam.library.dao.impl.BookDAOImpl;
import com.epam.library.dao.impl.EmployeeDAOImpl;
import com.epam.library.domain.*;

import java.util.List;

public class LibraryService {

    private LibraryService(){}

    private static BaseDAO bookDAO = new BookDAOImpl();
    private static BaseDAO employeeDAO = new EmployeeDAOImpl();
    private static AccountingDAO accountingDAO = new AccountingDAOImpl();

    public static void addEntity(Entity entity) {
        if (entity instanceof Book) {
            bookDAO.create(entity);
        } else if (entity instanceof Employee) {
            employeeDAO.create(entity);
        } else if (entity instanceof Accounting) {
            accountingDAO.create(entity);
        }
        System.out.println(LibraryConstants.Messages.Util.ADDED);
    }

    public static Entity getBookById(int id) {
        return bookDAO.read(id);
    }

    public static Entity getEmployeeById(int id) {
        return employeeDAO.read(id);
    }

    public static Entity getAccountingById(int id) {
        return  accountingDAO.read(id);
    }

    public static List<Entity> getBooks() {
        return bookDAO.readAll();
    }

    public static List<Entity> getEmployees() {
        return employeeDAO.readAll();
    }

    public static List<Entity> getAccountings() {
        return accountingDAO.readAll();
    }

    public static void updateBook(Book book) {
        bookDAO.update(book);
        System.out.println(LibraryConstants.Messages.Util.UPDATED);
    }

    public static void updateEmployee(Employee employee) {
        employeeDAO.update(employee);
        System.out.println(LibraryConstants.Messages.Util.UPDATED);
    }

    public static void updateAccounting(Accounting accounting) {
        accountingDAO.update(accounting);
        System.out.println(LibraryConstants.Messages.Util.UPDATED);
    }

    public static void deleteBookById(int id) {
        bookDAO.delete(id);
        System.out.println(LibraryConstants.Messages.Util.DELETED);
    }

    public static void deleteEmployeeById(int id) {
        employeeDAO.delete(id);
        System.out.println(LibraryConstants.Messages.Util.DELETED);
    }

    public static void deleteAccountingById(int id) {
        accountingDAO.delete(id);
        System.out.println(LibraryConstants.Messages.Util.DELETED);
    }

    public static List<AccountingData> getRentedInformation() {
        return accountingDAO.getRentedInformation();
    }

}
