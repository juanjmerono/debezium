package es.um.atica.debezium.demo.domain;

import java.util.UUID;

public class User {
    
    private String userId;
    private String userName;

    private User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public static User of(String userName) {
        return of(UUID.randomUUID().toString(), userName);
    }

    public static User of(String userId, String userName) {
        return new User(userId, userName);
    }

    public String userId() { return userId; }
    public String userName() { return userName; }

    public User changeUserName(String userName) {
        this.userName = userName;
        return this;
    }

}
