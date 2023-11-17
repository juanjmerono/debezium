package es.um.atica.debezium.demo.adapters.jpasource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSourceJPARepository extends JpaRepository<UserSourceEntity,String> {

}
