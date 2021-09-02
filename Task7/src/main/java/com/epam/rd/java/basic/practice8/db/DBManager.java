package com.epam.rd.java.basic.practice8.db;

import com.epam.rd.java.basic.practice8.db.constants.Constants;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static final Logger LOGGER = Logger.getLogger(DBManager.class.getName());
    private static DBManager dbManager;

    private DBManager() {
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection(String connectionUrl) throws SQLException {
        return DriverManager.getConnection(connectionUrl);
    }

    public static String readPropertiesFile(String propNameFile) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(propNameFile))) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }

        return properties.getProperty("connection.url");
    }

    public boolean insertUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_INSERT_NEW_USER,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getLogin());

            if (preparedStatement.executeUpdate() > 0) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    user.setId(rs.getLong(1));
                }
            }
            result = true;
        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return result;
    }

    public boolean insertTeam(Team team) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_INSERT_NEW_TEAM,
                    Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, team.getTeamName());

            if (preparedStatement.executeUpdate() > 0) {
                rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    team.setId(rs.getLong(1));
                }
            }
            result = true;
        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return result;
    }

    public List<User> findAllUsers() {
        List<User> userList = new LinkedList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            statement = conn.createStatement();
            rs = statement.executeQuery(Constants.SQL_FIND_ALL_USERS);
            while (rs.next()) {
                userList.add(buildUser(rs));
            }

        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(statement != null) statement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(conn != null) conn.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
        }
        return userList;
    }

    private User buildUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Constants.GET_USER_ID));
        user.setLogin(rs.getString(Constants.GET_USER_LOGIN));
        return user;
    }

    public List<Team> findAllTeams() {
        List<Team> teamsList = new LinkedList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            statement = conn.createStatement();
            rs = statement.executeQuery(Constants.SQL_FIND_ALL_TEAMS);
            while (rs.next()) {
                teamsList.add(buildTeam(rs));
            }

        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(statement != null) statement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(conn != null) conn.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
        }
        return teamsList;
    }

    private Team buildTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt(Constants.GET_TEAMS_ID));
        team.setName(rs.getString(Constants.GET_TEAM_NAME));
        return team;
    }

    public User getUser(String login) {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_FIND_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = buildUser(rs);
            }

        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return user;
    }

    public Team getTeam(String name) {
        Team team = new Team();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_FIND_TEAM_BY_NAME);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                team = buildTeam(rs);
            }

        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return team;
    }

    public void setTeamsForUser(User user, Team... teams) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            preparedStatement = connection.prepareStatement(Constants.SQL_SET_USER_TO_TEAM);

            for (Team t : teams) {
                preparedStatement.setLong(1, user.getId());
                preparedStatement.setLong(2, t.getId());
                preparedStatement.executeUpdate();
            }

            connection.commit();
        } catch (SQLException throwables) {
            if(connection != null) {
                connection.rollback();
            }
            throw throwables;
        } finally {
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
    }

    public List<Team> getUserTeams(User user) {
        List<Team> teams = new LinkedList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        String sqlCommand = Constants.NAME + user.getId() +
                Constants.CONTINUE_NAME;

        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            statement = connection.createStatement();

            rs = statement.executeQuery(sqlCommand); //NOSONAR
            while (rs.next()) {
                teams.add(getTeamById(rs.getLong("id")));
            }

        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(statement != null) statement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return teams;
    }

    public Team getTeamById(long id) {
        Team team = new Team();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_FIND_TEAM_BY_ID);
            preparedStatement.setLong(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                team = buildTeam(rs);
            }

        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(rs != null) rs.close();} catch (Exception e){LOGGER.log(Level.SEVERE,e.getMessage());}
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return team;
    }

    public boolean deleteTeam(Team team) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;

        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_DELETE_TEAM);
            preparedStatement.setLong(1, team.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return result;
    }

    public boolean updateTeam(Team team) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            connection = getConnection(readPropertiesFile(Constants.PROPERTIES_FILE_NAME));
            preparedStatement = connection.prepareStatement(Constants.SQL_UPDATE_TEAM);

            preparedStatement.setString(1, team.getTeamName());
            preparedStatement.setLong(2, team.getId());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException throwables) {
            LOGGER.log(Level.SEVERE, throwables.getMessage());
        } finally {
            try { if(preparedStatement != null) preparedStatement.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
            try { if(connection != null) connection.close();} catch (Exception e){LOGGER.log(Level.SEVERE,
                    e.getMessage());}
        }
        return result;
    }

}

