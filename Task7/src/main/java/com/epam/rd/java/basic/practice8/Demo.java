package com.epam.rd.java.basic.practice8;

import com.epam.rd.java.basic.practice8.db.DBManager;
import com.epam.rd.java.basic.practice8.db.entity.Team;
import com.epam.rd.java.basic.practice8.db.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) {
        final String delimeter = "=======================================";
        DBManager dbManager = DBManager.getInstance();

        //Part 1
        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));

        printList(dbManager.findAllUsers());
        System.out.println(delimeter);

        //Part 2
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));
        printList(dbManager.findAllTeams());

        System.out.println(delimeter);

        //Part 3
        User userIvanov = dbManager.getUser("ivanov");
        User userPetrov = dbManager.getUser("petrov");
        User userObama = dbManager.getUser("obama");

        Team teamA = dbManager.getTeam("teamA");
        Team teamB = dbManager.getTeam("teamB");
        Team teamC = dbManager.getTeam("teamC");
        try {
            dbManager.setTeamsForUser(userIvanov, teamA);
            dbManager.setTeamsForUser(userPetrov, teamA, teamB);
            dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);
        } catch (SQLException throwables) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE,throwables.getMessage());
        }

        for (User user : dbManager.findAllUsers()) {
            printList(dbManager.getUserTeams(user));
        }

        System.out.println(delimeter);

        //Part 4
        dbManager.deleteTeam(teamA);

        System.out.println(delimeter);

        //Part 5
        teamC.setName("teamX");
        dbManager.updateTeam(teamC);
        printList(dbManager.findAllTeams());
    }
}
