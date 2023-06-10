package common;

import controllers.models.Person;
import lombok.Data;

@Data // Adnotacja @Data wystarczy aby Lombok wygenerował getery, setery, toString,
      // equals i inne
public class TPerson {

    private String email;
    private String firstName;
    private String lastName;
    private int age;

    public TPerson() {
    }

    public TPerson(String email, String firstName, String lastName, int age) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public TPerson(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.age = person.getAge();
    }

}
