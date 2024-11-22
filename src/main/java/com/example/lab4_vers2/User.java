package com.example.lab4_vers2;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;
@Document(collection = "Users")
public class User {
    @Id
    private int id;
    private String username;
    private String email;
    private String password;
    private String passwordConfirm;
    private LocalDateTime accountCreationDate;


    public User(){}
    public User(int id, String username, String email, String password, String passwordConfirm) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm =  passwordConfirm;
        this.accountCreationDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Date getAccountCreationDateAsDate() {
        return Date.from(accountCreationDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public String getPasswordConfirm() {
        return  passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
          this.passwordConfirm = passwordConfirm;
    }
}