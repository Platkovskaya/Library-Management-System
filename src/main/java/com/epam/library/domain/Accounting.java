package com.epam.library.domain;

/**
 * Created by Irina on 21.04.2018.
 */
public class Accounting extends Entity {

    private int bookID;
    private int employeeID;

    public Accounting(int id, int bookID, int employeeID) {
        super(id);
        this.bookID = bookID;
        this.employeeID = employeeID;
    }

    public Accounting(int bookID, int emloyeeID) {
        this.bookID = bookID;
        this.employeeID = emloyeeID;
    }

    public int getBookID() {
        return bookID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    @Override
    public String toString() {
        return "★ id: " + getId() + ", bookID = " + bookID + ", employeeID = " + employeeID + " ★";
    }

}
