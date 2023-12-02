package es.um.atica.debezium.demo.adapters.debezium;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDebezium {
    
    @JsonProperty("USERID")
    private String userid;
    @JsonProperty("USERNAME")
    private String username;

}