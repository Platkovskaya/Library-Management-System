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


public class AddAndDeleteDataTest {

    private static final String REQUEST = "select * from book where title='BookTest' and brief='BriefTest' and author='AuthorTest' and publish_year=0000";

    @Test
    public void addAndDeleteDataTest() throws SQLException {

        //add
        Book book = new Book("BookTest", "BriefTest", "AuthorTest", 0000);
        LibraryService.addEntity(book);

        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();

        ResultSet addedResultSet = statement.executeQuery(REQUEST);

        int book_id = 0;
        int countOfBooks = 0;

        if (addedResultSet.last()) {
            countOfBooks = addedResultSet.getRow();
            book_id = addedResultSet.getInt("book_id");
        }

        Assert.assertEquals(countOfBooks, 1);

        //delete
        LibraryService.deleteBookById(book_id);

        ResultSet deletedResultSet = statement.executeQuery(REQUEST);

        countOfBooks = 0;
        if (deletedResultSet.last()) {
            countOfBooks = deletedResultSet.getRow();
        }

        Assert.assertEquals(countOfBooks, 0);

        DBConnection.closeConnection(connection);
    }

}
