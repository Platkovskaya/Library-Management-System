package com.epam.library.utils;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.rowset.CachedRowSetImpl;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.sql.rowset.CachedRowSet;
import java.lang.ref.ReferenceQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Irina on 21.04.2018.
 */
public class RequestUtils {

    private RequestUtils(){}

    public static void sendUpdateRequest(String request) {
        Connection connection = ConnectionUtils.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionUtils.closeConnection(connection);
    }

    public static CachedRowSet sendQueryRequest(String request) {

        Connection connection = ConnectionUtils.getConnection();
        CachedRowSet cachedRowSet = null;
        ResultSet resultSet = null;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(request);

            cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnectionUtils.closeConnection(connection);
        return cachedRowSet;
    }

}
