package com.epam.rd.java.basic.practice8.db.constants;

public class Constants {
    private Constants() {

    }
    public static String PROPERTIES_FILE_NAME = "app.properties"; //NOSONAR

    //For users
    public static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    public static final String SQL_INSERT_NEW_USER = "INSERT INTO users (login) VALUES (?)";
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login LIKE ? ";
    public static final String GET_USER_ID = "id";
    public static final String GET_USER_LOGIN = "login";

    //For teams
    public static final String SQL_FIND_ALL_TEAMS = "SELECT * FROM teams";
    public static final String SQL_INSERT_NEW_TEAM = "INSERT INTO teams (name) VALUES (?)";
    public static final String SQL_FIND_TEAM_BY_NAME = "SELECT * FROM teams WHERE name LIKE ?";
    public static final String SQL_FIND_TEAM_BY_ID = "SELECT * FROM teams WHERE id LIKE ?";
    public static final String SQL_DELETE_TEAM = "DELETE FROM teams WHERE id = ?";
    public static final String SQL_UPDATE_TEAM = "UPDATE teams SET name = ? WHERE id = ?";
    public static final String GET_TEAMS_ID = "id";
    public static final String GET_TEAM_NAME = "name";

    //For users_teams
    public static final String SQL_SET_USER_TO_TEAM = "INSERT INTO users_teams (user_id, team_id) VALUES (?, ?)";

    public static final String NAME = "SELECT teams.id, teams.name FROM teams " +
            "JOIN users_teams ON teams.id = users_teams.team_id " +
            "WHERE users_teams.user_id = ";
    public static final String CONTINUE_NAME =  " ORDER BY teams.id;";
}
