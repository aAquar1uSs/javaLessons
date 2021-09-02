package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.constants.Constants;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import org.junit.*;
import org.mockito.Spy;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class Part2StudentTest {
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
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException throwables) {
            Assert.fail();
        }
        Assert.assertTrue(true);
    }

    @Test
    public void shouldReturnFalseInMethodInsertTeamIfDataNotCorrect() {
        Constants.PROPERTIES_FILE_NAME = "adasdasdas";
        Team team = Team.createTeam("ghost");
        Assert.assertFalse(dbManager.insertTeam(team));
    }

    @Test
    public void shouldInsertTeam() {
        String newUser = "teamB";
        Team teamB = Team.createTeam(newUser);
        dbManager.insertTeam(teamB);
        teamB = dbManager.getTeam(newUser);
        Assert.assertEquals(1, teamB.getId());

    }

    @Test
    public void shouldPrintTeamName() {
        Team teamB = dbManager.getTeam("teamB");
        String expected = "Team name: teamB, ID: 1";
        Assert.assertEquals(expected, teamB.toString());
    }

    @Test
    public void testEqualsMethod() {
        Assert.assertTrue(dbManager.getTeam("teamB").equals(dbManager.getTeam("teamB")));
    }

    @Test
    public void shouldReturnListOfTeams() {
        Team newTeam = Team.createTeam("teamC");
        dbManager.insertTeam(newTeam);
        List<Team> list = dbManager.findAllTeams();
        Assert.assertTrue(list.size() == 2 && list.get(0).getTeamName().equals("teamB") &&
                list.get(1).getTeamName().equals("teamC"));
    }

    @Test
    public void shouldDeleteTeam() {
        String newTeam = "teamQ";
        Team teamQ = Team.createTeam(newTeam);
        dbManager.insertTeam(teamQ);
        Assert.assertTrue(dbManager.deleteTeam(teamQ));
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