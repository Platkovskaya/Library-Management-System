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

/**
 * Created by Irina on 27.04.2018.
 */
public class UpdateDataTest {

    private static final String REQUEST = "select * from book where book_id=10";

    @Test
    public void updateDataTest() throws SQLException {

        Book expectedBook = new Book(
                10,
                "UpdateTitle",
                "BriefTest",
                "AuthorTest",
                0000
        );

        LibraryService.updateBook(expectedBook);

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(REQUEST);

        Book actualBook = null;
        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    actualBook = new Book(
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

        Assert.assertEquals(expectedBook, actualBook);

        DBConnection.closeConnection(connection);
    }

}
