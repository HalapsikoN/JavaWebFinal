package by.epam.finalTask.entity;

import by.epam.finalTask.entity.util.Role;

import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String login;
    private Role role;
    private double wallet;

    public User() {
    }

    public User(String name, String login, double wallet) {
        this.name = name;
        this.login = login;
        this.wallet = wallet;
    }

    public User(String name, String login, Role role) {
        this.name = name;
        this.login = login;
        this.role = role;
    }

    public User(String name, String login, Role role, double wallet) {
        this.name = name;
        this.login = login;
        this.role = role;
        this.wallet = wallet;
    }

    public User(int id, String name, String login, Role role, double wallet) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.role = role;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.wallet, wallet) != 0) return false;
        if (!Objects.equals(name, user.name)) return false;
        if (!Objects.equals(login, user.login)) return false;
        return role == user.role;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + role.hashCode();
        temp = Double.doubleToLongBits(wallet);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", wallet=" + wallet +
                '}';
    }
}
