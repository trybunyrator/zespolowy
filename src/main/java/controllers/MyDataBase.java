package controllers;

import common.TPerson;
import controllers.models.Authority;
import controllers.models.Category;
import controllers.models.Comment;
import controllers.models.Person;
import controllers.models.Post;
import controllers.models.User;
import controllers.models.Uzytkownik;
import controllers.repositories.AuthorityRepository;
import controllers.repositories.CategoryRepository;
import controllers.repositories.CommentRepository;
import controllers.repositories.PersonRepository;
import controllers.repositories.PostRepository;
import controllers.repositories.UserRepository;
import controllers.repositories.UzytkownikRepository;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class MyDataBase {

        @Autowired
        PersonRepository personRepository;

        @Autowired
        CategoryRepository categoryRepository;

        @Autowired
        PostRepository postRepository;

        @Autowired
        CommentRepository commentRepository;

        @Autowired
        UzytkownikRepository uzytkownikRepository;

        @Autowired
        UserRepository userRepository;

        @Autowired
        AuthorityRepository authorityRepository;

        public MyDataBase() {
        }

        public void addPerson(String email, String firstName, String lastName, int age) {

                Person locPerson = personRepository.findByEmail(email);

                if (locPerson != null) {
                        throw new IllegalArgumentException("Użytkownik " + email + " już istnieje w bazie");
                }

                Person person = new Person(email, firstName, lastName, age);
                personRepository.save(person);
        }

        // public ArrayList<TPerson> getPersonList() {

        // List<Person> personList = personRepository.findAll();

        // if (personList == null) {
        // throw new IllegalArgumentException("Nie ma danych");
        // }

        // ArrayList<TPerson> locPersonList = new ArrayList<>();

        // for (int i = 0; i < personList.size(); i++) {
        // Person person = personList.get(i);
        // TPerson locUser = new TPerson(person);
        // locPersonList.add(locUser);
        // }

        // return locPersonList;
        // }

        // public void deletePerson(String emailToRemove) {

        // Person locPerson = personRepository.findByEmail(emailToRemove);

        // if (locPerson == null) {
        // throw new IllegalArgumentException("Użytkownik " + emailToRemove + " nie
        // istnieje w bazie");
        // }

        // personRepository.delete(locPerson);
        // }

        // FUNCKJA DO TESTOWANIA INSERTÓW I POPRAWNOŚCI DANYCH W ZWIĄZKU Z KLUCZAMI I
        // RELACJAMI

        // testowe dodawanie kategorii w celu sprawdzenia poprawnosci
        public void addCategory() {
                // sekcja testowania wstawiania kategorii
                Category newCategory = new Category("Informatyka");
                Category newCategory1 = new Category("Wędkarstwo");
                Category newCategory2 = new Category("Elektronika");
                Category newCategory3 = new Category("Budownictwo");
                Category newCategory4 = new Category("Mechanika");
                Category newCategory5 = new Category("Inne");

                categoryRepository.save(newCategory);
                categoryRepository.save(newCategory1);
                categoryRepository.save(newCategory2);
                categoryRepository.save(newCategory3);
                categoryRepository.save(newCategory4);
                categoryRepository.save(newCategory5);

                // czas
                Date javaDate = new Date();
                long javaTime = javaDate.getTime();
                Timestamp sqlTimestamp = new Timestamp(javaTime);

                // sekcja testowa postów
                Post testPost = new Post(newCategory, sqlTimestamp, "test znakow", "testowaazwa");
                Post testPost1 = new Post(newCategory, sqlTimestamp, "testznakow1111111111111",
                                "testowa nazwa1111111111111");
                postRepository.save(testPost);
                postRepository.save(testPost1);
                // sekcja testowa komentarzy

                Comment newComment = new Comment(testPost, sqlTimestamp, "Super post        użytkowniku", null,
                                "admin");
                Comment newComment1 = new Comment(testPost, sqlTimestamp, "Super        postżytkowniku", newComment,
                                "admin");
                Comment newComment2 = new Comment(testPost1, sqlTimestamp,
                                "Super post        użytkowniku111111111111111111111111111111111", null, "admin");

                commentRepository.save(newComment);
                commentRepository.save(newComment1);
                commentRepository.save(newComment2);

                Uzytkownik newUzytkownik1 = new Uzytkownik("admin",
                                "$2a$10$Wu6dMFBjiEscLeC6mN/sAemKfVum0BBpn0t6xQNcF1L91lqktHkia", "ADMIN"); // haslo 1234
                Uzytkownik newUzytkownik2 = new Uzytkownik("test",
                                "$2a$10$Wu6dMFBjiEscLeC6mN/sAemKfVum0BBpn0t6xQNcF1L91lqktHkia", "USER"); // haslo 1234
                uzytkownikRepository.save(newUzytkownik1);
                uzytkownikRepository.save(newUzytkownik2);

                // Robert Macłowicz poleca
                User nowyAdmin = new User("admin", "$2a$10$QVBrjqpJ9EetDoRT6Dpd7.8vanjfSrNUC2zGWtnu9bdC3BhBtpd/e",
                                true);
                userRepository.save(nowyAdmin);
                Authority noweAuthority = new Authority("admin", "ROLE_ADMIN");
                authorityRepository.save(noweAuthority);

        }

}
