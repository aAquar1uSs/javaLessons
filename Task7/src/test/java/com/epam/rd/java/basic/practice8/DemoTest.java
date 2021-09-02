package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import org.junit.*;
import org.mockito.Spy;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoTest {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test";
    private static final String URL_CONNECTION = "jdbc:h2:~/test;user=user;password=user1234;";
    private static final String USER = "user";
    private static final String PASS = "user1234";

    private final ByteArrayOutputStream outputConsole = new ByteArrayOutputStream();

    @Spy
    private static DBManager dbManager;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputConsole));
    }

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
    public void shouldTestOutputInformationDemo() {
        Demo.main(null);
        if(!outputConsole.toString().equals("")) {
            Assert.assertNotEquals("", outputConsole.toString());
        } else
            Assert.fail();
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
