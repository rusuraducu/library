package Biblioteca.DAO.User;

import Biblioteca.DAO.Rank.Rank;

import java.sql.Date;

public class UserFilter {
    private int id;
    private String username;
    private Rank rank;
    private Date joined;
    private int rowLimit;
    private int status;

    public int getRowLimit() {
        return rowLimit;
    }

    public void setRowLimit(int rowLimit) {
        this.rowLimit = rowLimit;
    }

    public UserFilter(int id) {
        this.id = id;
    }



    public UserFilter(String username) {
        this.username = username;
    }

    public UserFilter(Rank rank) {
        this.rank = rank;
    }

    public UserFilter(Date joined) {
        this.joined = joined;
    }

    public UserFilter(Rank rank, Date joined) {
        this.rank = rank;
        this.joined = joined;
    }

    public UserFilter(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public int getStatus() {
        return status;
    }
}
