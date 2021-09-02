package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.constants.Constants;
import com.epam.rd.java.basic.practice8.db.entity.Team;
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

import static org.junit.Assert.assertEquals;

public class Part3StudentTest {
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
            String sqlRequestCreateUsers = "CREATE TABLE IF NOT EXISTS users (" +
                    "  id INTEGER(11) NOT NULL AUTO_INCREMENT, " +
                    " login VARCHAR(20) NOT NULL, " +
                    "  PRIMARY KEY (id));";

            statement.executeUpdate(sqlRequestCreateUsers);

            String sqlRequestCreateTeams = "CREATE TABLE IF NOT EXISTS teams (" +
                    "  id INT NOT NULL AUTO_INCREMENT," +
                    "  name VARCHAR(10) NOT NULL," +
                    "  PRIMARY KEY (id));";

            statement.executeUpdate(sqlRequestCreateTeams);

            String sqlRequestCreateUsersTeams = "CREATE TABLE users_teams (" +
                    "user_id INT REFERENCES users(id) ON DELETE CASCADE," +
                    "team_id INT REFERENCES teams(id) ON DELETE CASCADE," +
                    "UNIQUE (user_id, team_id)" +
                    ");";

            statement.executeUpdate(sqlRequestCreateUsersTeams);
        }
    }

    @Test
    public void shouldReturnErrorInMethodSetTeamsForUserIfDataNotCorrect() {
        Constants.PROPERTIES_FILE_NAME = "adasdasdas";
        Team team1 = Team.createTeam("teamA");
        Team team2 = Team.createTeam("teamB");
        User user = User.createUser("aboba");
        try {
            dbManager.setTeamsForUser(user, team1, team2);
        } catch (SQLException e) {
            assertEquals("The url cannot be null", e.getMessage());
        }
    }

    @Test
    public void shouldGetUser() {
        User ivanov = User.createUser("ivanov");
        User petrov = User.createUser("petrov");
        dbManager.insertUser(ivanov);
        dbManager.insertUser(petrov);

        String expected = "Login: ivanov, ID: 1";

        assertEquals(expected, dbManager.getUser("ivanov").toString());
    }

    @Test
    public void shouldGetTeam() {
        Team teamA = Team.createTeam("teamA");
        Team teamB = Team.createTeam("teamB");
        dbManager.insertTeam(teamA);
        dbManager.insertTeam(teamB);

        String expected = "Team name: teamB, ID: 2";
        assertEquals(expected, dbManager.getTeam("teamB").toString());
    }

    @Test
    public void shouldSetTeamsForUser() {
        User ivanov = dbManager.getUser("ivanov");
        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        try {
            dbManager.setTeamsForUser(ivanov, teamA, teamB);
        } catch (SQLException throwables) {
            Assert.fail();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (User user : dbManager.findAllUsers()) {
            stringBuilder.append(dbManager.getUserTeams(user));
        }
        String expected = "[Team name: teamA, ID: 1, Team name: teamB, ID: 2][]";
        assertEquals(expected, stringBuilder.toString().trim());
    }

    @After
    public void setFile() {
        Constants.PROPERTIES_FILE_NAME = "app.properties";
    }

    @AfterClass
    public static void delete() {
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = con.createStatement()) {
            String sqlDropTableUser = "DROP TABLE users;";
            statement.executeUpdate(sqlDropTableUser);

            String sqlDropTableTeam = "DROP TABLE teams;";
            statement.executeUpdate(sqlDropTableTeam);

            String sqlDropTableUsersTeams = "DROP TABLE users_teams";
            statement.executeUpdate(sqlDropTableUsersTeams);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}