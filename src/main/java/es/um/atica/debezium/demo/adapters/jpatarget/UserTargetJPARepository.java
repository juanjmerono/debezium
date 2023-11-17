package es.um.atica.debezium.demo.adapters.jpatarget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTargetJPARepository extends JpaRepository<UserTargetEntity,String> {

}
