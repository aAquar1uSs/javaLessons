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

public class Part4StudentTest {
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
    public void shouldDeleteCascade() {
        Team teamA = Team.createTeam("teamA");
        Team teamB = Team.createTeam("teamB");
        dbManager.insertTeam(teamA);
        dbManager.insertTeam(teamB);
        dbManager.deleteTeam(teamA);

        StringBuilder stringBuilder = new StringBuilder();
        for (Team team : dbManager.findAllTeams()) {
            stringBuilder.append(team.toString());
        }

        String expected = "Team name: teamB, ID: 2";
        Assert.assertEquals(expected, stringBuilder.toString().trim());
    }

    @Test
    public void shouldGetTeamById() {
        Team team = dbManager.getTeamById(2);
        String expected = "Team name: teamB, ID: 2";
        Assert.assertEquals(expected,team.toString());
    }

    @Test
    public void shouldReturnFalseInMethodDeleteTeamIfDataNotCorrect() {
        Constants.PROPERTIES_FILE_NAME = "dasdaxcz";
        Team teamA = Team.createTeam("teamA");
        dbManager.insertTeam(teamA);
        Assert.assertFalse(dbManager.deleteTeam(teamA));
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