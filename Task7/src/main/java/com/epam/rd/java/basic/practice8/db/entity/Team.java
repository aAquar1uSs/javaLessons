package com.epam.rd.java.basic.practice8.db.entity;


public class Team {
    private long id;
    private String teamName;

    public static Team createTeam(String groupName) {
        Team team = new Team();
        team.setName(groupName);
        return team;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String groupName) {
        this.teamName = groupName;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (o == this) {
            return true;
        }

        Team team = (Team) o;
        return (this.getTeamName().equals(team.getTeamName()));
    }

    @Override
    public int hashCode() {
        int hash = 1;
        final int PRIME = 31;
        hash = hash * PRIME + teamName.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Team name: " +  teamName + ", ID: " + id;
    }

}
