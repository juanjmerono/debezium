package es.um.atica.debezium.demo.adapters.jpasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.um.atica.debezium.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="USERS")
@Getter
public class UserSourceEntity {
    
    @Id
    private String userid;

    @Column
    private String username;

    public static UserSourceEntity of(User u) {
        return new UserSourceEntity(u.userId(),u.userName());
    }

    public User toModel() {
        return User.of(this.userid, this.username);
    }
}
