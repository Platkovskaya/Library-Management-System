package test.service;

import com.epam.library.domain.Book;
import com.epam.library.service.LibraryService;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ReadDataByIdTest {

    private static final String REQUEST = "select * from book where book_id=6";

    @Test
    public void readDataByIdTest() throws SQLException {

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(REQUEST);

        Book expectedBook = null;
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    expectedBook = new Book(
                            resultSet.getInt("book_id"),
                            resultSet.getString("title"),
                            resultSet.getString("brief"),
                            resultSet.getString("author"),
                            resultSet.getInt("publish_year")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Book actualBook = (Book) LibraryService.getBookById(6);

        Assert.assertEquals(expectedBook, actualBook);

        DBConnection.closeConnection(connection);
    }

}
