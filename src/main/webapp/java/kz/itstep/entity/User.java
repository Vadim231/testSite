package kz.itstep.entity;

import java.sql.Date;

public class User extends Entity {
    private String login;
    private String password;
    private String name;
    private String surname;
    private int roleId;
    private byte[] image;




    public User(String login, String password, String name, String surname, int roleId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
    }

    public User(){}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getRole() {
        return roleId;
    }

    public void setRole(int roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String toString(){
        return name + " " + surname;
    }
}
