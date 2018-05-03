package com.epam.library.constants;

public class LibraryConstants {

    private LibraryConstants(){}

    public static class Messages {

        public static class Menu {
            public static final String CATEGORY_ITEM = "Input category 'item', please...";
            public static final String MENU_ITEM = "Input menu 'item', please...";
            public static final String REPORT_GENERATED = "Report was generated!";
            public static final String EXIT = "System is shutting down...";
        }

        public static class Input {

            public static class Book {
                public static final String ID = "Input book 'id', please...";
                public static final String TITLE = "Input book 'title', please...";
                public static final String BRIEF = "Input book 'brief', please...";
                public static final String AUTHOR = "Input book 'author', please...";
                public static final String YEAR = "Input book 'year', please...";
            }

            public static class Employee {
                public static final String ID = "Input employee 'id', please...";
                public static final String NAME = "Input employee 'name', please...";
                public static final String EMAIL = "Input employee 'email', please...";
                public static final String DATE_OF_BIRTH = "Input employee 'date of birth', please...";
            }

            public static class Accounting {
                public static final String ID = "Input accounting 'id', please...";
                public static final String BOOK_ID = "Input book 'id', please...";
                public static final String EMPLOYEE_ID = "Input employee 'id', please...";
            }
        }

        public class Util {
            public static final String ADDED = "Record was added!";
            public static final String UPDATED = "Record was updated!";
            public static final String DELETED = "Record was deleted!";
            public static final String SUB_EXIT = "Exit from sub-menu...";
        }

    }

    public static class DataBase {

        private static final String BOOK = "book";
        private static final String EMPLOYEE = "employee";
        private static final String ACCOUNNTING = "employee_book";

        public static class Property {
            public static final String CONNECT = "db_config";
            public static final String URL = "db.url";
            public static final String LOGIN = "db.login";
            public static final String PASSWORD = "db.pass";
            public static final String DRIVER = "db.driver";
        }

        public static class Commands {

            public static class General {
                public static final String BOOK_EMPLOYEE_JOIN = "SELECT * FROM (" + BOOK + " JOIN " + ACCOUNNTING + " USING (book_id)) JOIN " + EMPLOYEE + " USING (employee_id);";
            }

            public static class Book {
                public static final String SELECT = "select * from " + BOOK;
                public static final String ADD = "insert into " + BOOK + " (title, brief, author, publish_year) values('%s', '%s', '%s', %s)";
                public static final String UPDATE = "update " + BOOK + " set title='%s', author='%s', brief='%s', publish_year='%s' where book_id=%s";
                public static final String DELETE = "delete from " + BOOK + " where book_id='%s'";
                public static final String SELECT_BY_ID = "select * from " + BOOK + " where book_id = %s";
            }

            public static class Employee {
                public static final String SELECT = "select * from " + EMPLOYEE;
                public static final String ADD = "insert into " + EMPLOYEE + " (name, email, date_of_birth) values('%s', '%s', '%s')";
                public static final String UPDATE = "update " + EMPLOYEE + " set name='%s', email='%s', date_of_birth='%s' where employee_id=%s";
                public static final String DELETE = "delete from " + EMPLOYEE + " where employee_id='%s'";
                public static final String SELECT_BY_ID = "select * from " + EMPLOYEE + " where employee_id = %s";
            }

            public static class Accounting {
                public static final String SELECT = "select * from " + ACCOUNNTING;
                public static final String ADD = "insert into " + ACCOUNNTING + " (book_id, employee_id) values(%s, %s)";
                public static final String UPDATE = "update " + ACCOUNNTING + " set book_id='%s', employee_id='%s' where book_id=%s";
                public static final String DELETE = "delete from " + ACCOUNNTING + " where id='%s'";
                public static final String SELECT_BY_ID = "select * from " + ACCOUNNTING + " where id = %s";
            }

        }
    }

    public static class Path {
        public static final String REPORT_PATH = "C:/Users/progr/Desktop/Library/src/main/resources/report/report.html";
    }

}