package com.epam.library.utils;

import com.epam.library.domain.AccountingData;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;
import com.epam.library.service.LibraryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Irina on 22.04.2018.
 */
public class AccountingUtils {

    private AccountingUtils(){}

    public static List<Employee> getAccoutingDataByCondition(int from, int to) {

        List<AccountingData> accountingDataList = LibraryService.getRentedInformation();

        List<Employee> employees = new ArrayList<>();

        for (AccountingData data : accountingDataList) {

            Book book = data.getBook();
            Employee employee = data.getEmployee();

            if (employees.contains(employee)) {
                int index = employees.indexOf(employee);
                Employee existedEmployee = employees.get(index);
                existedEmployee.addRentedBook(book);
                employees.set(index, existedEmployee);
            } else {
                employee.addRentedBook(book);
                employees.add(employee);
            }
        }

        List<Employee> conditionEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getRentedBooks().size() > from && employee.getRentedBooks().size() < to) {
                conditionEmployees.add(employee);
            }
        }

        return conditionEmployees;
    }

}
