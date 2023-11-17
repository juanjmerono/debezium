package es.um.atica.debezium.demo.adapters.rest;

import es.um.atica.debezium.demo.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDTO {
    
    private String userid;
    private String username;

    public static UserDTO of (User u) {
        return UserDTO.builder().userid(u.userId()).username(u.userName()).build();
    }

}
