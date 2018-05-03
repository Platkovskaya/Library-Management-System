package com.epam.library.domain;

/**
 * Created by Irina on 22.04.2018.
 */
public class AccountingData {

    private Book book;
    private Employee employee;
    private Accounting accounting;

    public AccountingData(Book book, Employee employee, Accounting accounting) {
        this.book = book;
        this.employee = employee;
        this.accounting = accounting;
    }

    public Book getBook() {
        return book;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Accounting getAccounting() {
        return accounting;
    }

}
