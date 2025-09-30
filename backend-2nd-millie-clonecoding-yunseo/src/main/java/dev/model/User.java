package dev.model;

public class User {
    private int id;
    private String nickname;
    private String password; 

    public User() {
    }

    // Constructor with all fields
    public User(int id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPw() {
        return password;
    }

    public void setPw(String pw) {
        this.password = password;
    }

    // Optional: toString() method for easy debugging
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", nickname='" + nickname + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}