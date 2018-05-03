package com.epam.library.dao.impl;

import com.epam.library.constants.LibraryConstants;
import com.epam.library.dao.AccountingDAO;
import com.epam.library.dao.BaseDAO;
import com.epam.library.domain.*;
import com.epam.library.utils.RequestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irina on 21.04.2018.
 */
public class AccountingDAOImpl implements AccountingDAO {

    @Override
    public Entity read(int id) {
        String request = String.format(
                LibraryConstants.DataBase.Commands.Accounting.SELECT_BY_ID,
                id
        );

        ResultSet resultSet = RequestUtils.sendQueryRequest(request);

        Accounting accounting = null;
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    accounting = new Accounting(
                            resultSet.getInt("id"),
                            resultSet.getInt("book_id"),
                            resultSet.getInt("employee_id")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounting;
    }

    @Override
    public List<Accounting> readAll() {

        String request = LibraryConstants.DataBase.Commands.Accounting.SELECT;

        ResultSet resultSet = RequestUtils.sendQueryRequest(request);

        List<Accounting> accountings = new ArrayList<>();
        try {
            if (resultSet != null) {
                while (resultSet.next()) {

                    Accounting accounting = new Accounting(
                            resultSet.getInt("id"),
                            resultSet.getInt("book_id"),
                            resultSet.getInt("employee_id")
                    );

                    accountings.add(accounting);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountings;
    }

    @Override
    public void delete(int id) {

        String request = String.format(
                LibraryConstants.DataBase.Commands.Accounting.DELETE,
                id
        );

        RequestUtils.sendUpdateRequest(request);
    }

    @Override
    public void create(Entity entity) {

        Accounting accounting = (Accounting) entity;
        String request = String.format(
                LibraryConstants.DataBase.Commands.Accounting.ADD,
                accounting.getBookID(),
                accounting.getEmployeeID()
        );

        RequestUtils.sendUpdateRequest(request);
    }

    @Override
    public void update(Entity entity) {

        Accounting accounting = (Accounting) entity;

        String request = String.format(
                LibraryConstants.DataBase.Commands.Accounting.UPDATE,
                accounting.getBookID(),
                accounting.getEmployeeID(),
                accounting.getId()
        );

        RequestUtils.sendUpdateRequest(request);
    }

    @Override
    public List<AccountingData> getRentedInformation() {

        String request = LibraryConstants.DataBase.Commands.General.BOOK_EMPLOYEE_JOIN;

        ResultSet resultSet = RequestUtils.sendQueryRequest(request);

        List<AccountingData> accountingDataList = new ArrayList<>();
        try {
            if (resultSet != null) {
                while (resultSet.next()) {

                    Book book = new Book(
                            resultSet.getInt("book_id"),
                            resultSet.getString("title"),
                            resultSet.getString("brief"),
                            resultSet.getString("author"),
                            resultSet.getInt("publish_year")
                    );

                    Employee employee = new Employee (
                            resultSet.getInt("employee_id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getDate("date_of_birth")
                    );

                    Accounting accounting = new Accounting(
                            resultSet.getInt("id"),
                            resultSet.getInt("book_id"),
                            resultSet.getInt("employee_id")
                    );

                    AccountingData accountingData = new AccountingData(book, employee, accounting);

                    accountingDataList.add(accountingData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountingDataList;
    }

}
