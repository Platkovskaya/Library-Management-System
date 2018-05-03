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
import java.util.ArrayList;
import java.util.List;


public class ReadAllDataTest {

    private static final String REQUEST = "select * from book";

    @Test
    public void readAllDataTest() throws SQLException {

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(REQUEST);

        List<Book> expectedBooks = new ArrayList<>();
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
                    expectedBooks.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Book> actualBooks = (List<Book>) (List) LibraryService.getBooks();

        Assert.assertEquals(expectedBooks, actualBooks);

        DBConnection.closeConnection(connection);
    }

}
