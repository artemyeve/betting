package by.artemyeu.betting.entity;

import java.math.BigDecimal;

/**
 * Created by Acer on 01.06.2017.
 */
public class User extends Entity{
    private int id;
    private String firstName;
    private String secondName;
    private String login;
    private String password;
    private String email;
    private UserRole role;
    private BigDecimal balance;

    /**
     * Instantiates a new user.
     *
     * @param id the id
     * @param firstName the first name
     * @param secondName the second name
     * @param login the login
     * @param password the password
     * @param email the email
     * @param role the role
     * @param balance the balance
     */
    public User(int id, String firstName, String secondName, String login, String password, String email, UserRole role,BigDecimal balance) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.balance = balance;
    }

    public User(int id, String login) {
        this.id = id;
        this.login = login;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
