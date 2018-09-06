package Biblioteca.DAO.User;

import Biblioteca.Actions.CurrentDate;
import Biblioteca.DAO.Rank.Rank;
import Biblioteca.Queries.UserQueries;

import java.sql.Date;

public class User {

    private static UserQueries QUERIES = new UserQueries();

    private int id;
    private String username;
    private String password;
    private String email;
    private Rank rank;
    private String firstname;
    private String secondname;
    private Date joined;
    private String status; // Active | Inactive

    public User(String username, String password, Rank rank, String email, String firstname, String secondname) {
        this.username = username;
        this.password = password;
        this.rank = rank;
        this.email = email;
        this.firstname = firstname;
        this.secondname = secondname;
        this.joined = new CurrentDate().getCurrentDate();
    }
    public User(String username, Rank rank, String email, Date joined) {
        this.username = username;
        this.rank = rank;
        this.email = email;
        this.joined = joined;
    }
    public User(String username, Rank rank, String email, String firstname, String secondname, Date joined) {
        this.username = username;
        this.rank = rank;
        this.email = email;
        this.firstname = firstname;
        this.secondname = secondname;
        this.joined = joined;
    }

    public User(int id, String username, String password, String email, Rank rank, String firstname, String secondname, Date joined, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rank = rank;
        this.firstname = firstname;
        this.secondname = secondname;
        this.joined = joined;
        this.status = status;
    }

    public boolean isActive(User user){
        if(!user.getStatus().equals("Active")){
            System.out.println("This username is inactive/deleted!");
            return false;
        }
        return true;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                " " + username  + "  " +

                " Email: " + email  + "   "+

                " Rank: " + rank.getRankName() +"   "+

                " Joined: " + joined;
    }
}
