package controllers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import controllers.models.Authority;
import controllers.models.User;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
