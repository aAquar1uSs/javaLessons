package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.constants.Constants;
import com.epam.rd.java.basic.practice8.db.entity.User;

import org.junit.*;
import org.mockito.Spy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;


public class Part1StudentTest {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=user;password=user1234;";
    private static final String USER = "user";
    private static final String PASS = "user1234";

    @Spy
    private static DBManager dbManager;

    @BeforeClass
    public static void beforeTest() throws SQLException {

        try (OutputStream output = new FileOutputStream("app.properties")) {
            Properties prop = new Properties();
            prop.setProperty("connection.url", URL_CONNECTION);
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

        dbManager = DBManager.getInstance();

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "  id INTEGER(11) NOT NULL AUTO_INCREMENT, " +
                    " login VARCHAR(20) NOT NULL, " +
                    "  PRIMARY KEY (id));";

            statement.executeUpdate(sql);
        }
    }

    @Test
    public void testConnection() {
        try {
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }


    @Test
    public void shouldReturnFalseInMethodInsertUserIfDataNotCorrect() {
        Constants.PROPERTIES_FILE_NAME = "adasdasdas";
        User user = User.createUser("ghost");
        Assert.assertFalse(dbManager.insertUser(user));
    }

    @Test
    public void shouldInsertUser() {
        String newUser = "ivanov";
        User ivanov = User.createUser(newUser);
        Assert.assertTrue(dbManager.insertUser(ivanov));
    }

    @Test
    public void shouldPrintCorrectData() {
        String expected = "Login: ivanov, ID: 1";
        Assert.assertEquals(expected,dbManager.getUser("ivanov").toString());
    }

    @Test
    public void testEqualsMethod() {
        Assert.assertTrue(dbManager.getUser("ivanov").equals(dbManager.getUser("ivanov")));
    }

    @Test
    public void shouldGetIdUser() {
        User newUser = User.createUser("obama");
        dbManager.insertUser(newUser);
        Assert.assertEquals(2, dbManager.getUser("obama").getId());
    }

    @Test
    public void testEqualsMethodIfDataDoNotMatch() {
        Assert.assertFalse(dbManager.getUser("ivanov").equals(dbManager.getUser("obama")));
    }

    @Test
    public void shouldPrintUserLogin() {
        User ivanov = dbManager.getUser("ivanov");
        Assert.assertEquals("ivanov", ivanov.getLogin());
    }

    @Test
    public void shouldReturnListOfUsers() {
        List<User> list = dbManager.findAllUsers();

        Assert.assertTrue(list.size() == 2 && list.get(0).getLogin().equals("ivanov") &&
                list.get(1).getLogin().equals("obama"));
    }

    @After
    public void setFile() {
        Constants.PROPERTIES_FILE_NAME = "app.properties";
    }

    @AfterClass
    public static void delete() {
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "DROP TABLE users;";

            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
