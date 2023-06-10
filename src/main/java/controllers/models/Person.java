package controllers.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import common.TPerson;
import lombok.Data;

@Data // Adnotacja @Data wystarczy aby Lombok wygenerował getery, setery, toString,
      // equals i inne
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "age")
    int age;

    public Person() {
        this.email = null;
        this.firstName = null;
        this.lastName = null;
        this.age = -1;
    }

    public Person(String email, String firstName, String lastName, int age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(TPerson tUser) {
        this.email = tUser.getEmail();
        this.firstName = tUser.getFirstName();
        this.lastName = tUser.getLastName();
        this.age = tUser.getAge();
    }

}
