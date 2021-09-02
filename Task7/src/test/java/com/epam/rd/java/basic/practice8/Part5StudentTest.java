package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.constants.Constants;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.*;
import org.mockito.Spy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Part5StudentTest {
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
            String sql = "CREATE TABLE IF NOT EXISTS teams (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(10) NOT NULL," +
                    "  PRIMARY KEY (id));";
            statement.executeUpdate(sql);
        }
    }

    @Test
    public void testConnection() {
        try {
            Connection connect = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void shouldUpdateTeam() {
        Team teamC = Team.createTeam("teamC");
        dbManager.insertTeam(teamC);
        teamC.setName("teamX");
        dbManager.updateTeam(teamC);

        String expected = "Team name: teamX, ID: 1";
        Assert.assertEquals(expected, dbManager.getTeam("teamX").toString());
    }

    @Test
    public void shouldDeleteTeam() {
        Assert.assertTrue(dbManager.deleteTeam(dbManager.getTeam("teamX")));
    }

    @After
    public void setFile() {
        Constants.PROPERTIES_FILE_NAME = "app.properties";
    }

    @AfterClass
    public static void delete() {
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sql = "DROP TABLE teams;";

            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}