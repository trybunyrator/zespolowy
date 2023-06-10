package controllers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import controllers.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    public Person findByEmail(String email);

    public Person findByFirstNameAndLastName(String firstName, String lastName);

}
