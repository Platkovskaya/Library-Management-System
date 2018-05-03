package com.epam.library.utils;

import com.epam.library.domain.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Irina on 22.04.2018.
 */
public class SortUtils {

    private SortUtils(){}

    public static void sortByCountOfBooks(List<Employee> employees) {
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Integer.compare(o1.getRentedBooks().size(), o2.getRentedBooks().size());
            }
        });
    }

    public static void sortByBirthDate(List<Employee> employees) {
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o2.getDateOfBirth().compareTo(o1.getDateOfBirth());
            }
        });
    }

}
