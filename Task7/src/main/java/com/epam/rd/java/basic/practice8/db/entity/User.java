package com.epam.rd.java.basic.practice8.db.entity;


public class User {
    private long id = 0;
    private String login;

    public static User createUser(String login) {
        User user = new User();
        user.setLogin(login);
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (o == this) {
            return true;
        }

        User user = (User) o;
        return (this.getLogin().equals(user.getLogin()));
    }

    @Override
    public int hashCode() {
        int hash = 1;
        final int PRIME = 31;
        hash = hash * PRIME + login.hashCode();
        return hash;
    }


    @Override
    public String toString() {
        return "Login: " + login + ", ID: " +id;
    }

}
