package controllers;

//To jest kontroler do przykładu z baza osób

import common.TPerson;
import controllers.models.Authority;
import controllers.models.Category;
import controllers.models.Comment;
import controllers.models.Post;
import controllers.models.User;
import controllers.models.Uzytkownik;

import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MyController {

    @Autowired
    MyDataBase personDataBase;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // testowy endpoint
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String systemInfo() {

        return "Testowy serwis webowy";
    }

    // wyświetlanie wszystkich kategorii
    @RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
    public List<Category> getAllCategory() {
        // System.out.println(personDataBase.categoryRepository.findAll());
        return personDataBase.categoryRepository.findAll();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public List<Comment> test() {
        System.out.println(personDataBase.commentRepository.findAll());
        return personDataBase.commentRepository.findAll();
    }

    // wyświetlanie komentarzy do danego id posta - UWAGA - NIE PODKOMENTARZY
    // (IDPARENT NULL)
    @RequestMapping(value = "/commentsPost/{id}", method = RequestMethod.GET)
    public List<Comment> getCommentsPost(@PathVariable("id") Post id) {
        System.out.println(personDataBase.commentRepository.findAllByIdPostIsAndIdParentNull(id));
        return personDataBase.commentRepository.findAllByIdPostIsAndIdParentNull(id);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable("id") long id) {
        System.out.println(personDataBase.postRepository.findById(id));
        return personDataBase.postRepository.findById(id);
    }

    // wyświetlanie komentarzy do DANEGO KOMENTARZA
    @RequestMapping(value = "/commentsComment/{id}", method = RequestMethod.GET)
    public List<Comment> getCommentsPost(@PathVariable("id") Comment id) {
        System.out.println(personDataBase.commentRepository.findAllByIdParentIs(id));
        return personDataBase.commentRepository.findAllByIdParentIs(id);
    }

    // wyświetlanie wszystkich postów w danej kategorii
    @RequestMapping(value = "/getAllPostsInCategory/{id}", method = RequestMethod.GET)
    public List<Post> getAllPostsCategory(@PathVariable("id") Category id) {
        System.out.println(personDataBase.postRepository.findAllByIdCategoryIs(id));
        return personDataBase.postRepository.findAllByIdCategoryIs(id);
    }

    // delete komentarza o podanym id
    @RequestMapping(value = "/deleteComment/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable("id") Long id) {
        if (personDataBase.commentRepository.existsById(id)) {
            personDataBase.commentRepository.deleteById(id);
            System.out.println("Usunięto");
        } else {
            System.out.println("komentarz o takim id nie istanieje");
        }

        return "deleteComment/{id}";
    }

    // delete komentarza o podanym id
    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") Post id) {
        long idDelete = id.getIdPost();
        // NAPOTKANO PROBLEM Z USUWANIEM POSTA GDY MA KOMENTARZE
        if (personDataBase.commentRepository.existsByIdPost(id)) {
            // DECYZJA DO PODJECIA CO ROBIMY - USUWAMY KASKADOWO CZY ZOSTAWIAMY W TAKIM
            // PRZYPADKU
            System.out.println("Do danego postu są kometarze");
        } else {
            personDataBase.postRepository.deleteById(idDelete);
        }
        return "deletePost/{id}";
    }

    @RequestMapping(value = "/addNewPost", method = RequestMethod.POST)
    public ResponseEntity<String> handleFormSubmit(@RequestBody Post formData) {
        System.out.println(formData);
        System.out.println("0000");
        Category testowaCategory = new Category();

        System.out.println(testowaCategory);
        testowaCategory.setIdCategory(15);
        System.out.println(testowaCategory);
        // czas
        Date javaDate = new Date();
        long javaTime = javaDate.getTime();
        Timestamp sqlTimestamp = new Timestamp(javaTime);
        System.out.println("test1");
        Post nowyPost = new Post(testowaCategory, sqlTimestamp, formData.getContent(), formData.getTitle());
        personDataBase.postRepository.save(nowyPost);
        System.out.println(nowyPost);
        System.out.println("test2");
        // formData.getTitle());
        return ResponseEntity.ok("Dane odebrane poprawnie!");
    }

    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public ResponseEntity<String> handleFormSubmit(@RequestBody Uzytkownik user) {
        user.setLogin("test");
        user.setPassword("test123");
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Uzytkownik newUser = new Uzytkownik(user.getLogin(), hashedPassword, "USER");
        personDataBase.uzytkownikRepository.save(newUser);
        System.out.println(newUser);
        return ResponseEntity.ok("Dane odebrane poprawnie!");
    }

    @RequestMapping(value = "/addNewUser1", method = RequestMethod.GET)
    public ResponseEntity<String> handleFormSubmit2() {
        Uzytkownik user = new Uzytkownik();
        user.setLogin("test123");
        user.setPassword("test");
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Uzytkownik newUser = new Uzytkownik(user.getLogin(), hashedPassword, "USER");
        personDataBase.uzytkownikRepository.save(newUser);
        System.out.println(newUser);
        return ResponseEntity.ok("Dane odebrane poprawnie!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> handleLoginSubmit() {
        // Przetwarzanie danych logowania

        // String username1 = username;
        // String password1 = password;

        // Authentication authentication;
        // authentication = authenticationManager
        // .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // // Authentication authentication =
        // // SecurityContextHolder.getContext().getAuthentication();
        // // authentication.getPrincipal();
        // // System.out.println(authentication.getName());

        return ResponseEntity.ok("Dane odebrane poprawnie!");
    }

    @PostMapping("/addNewUserTest")
    public void addUser(@RequestBody Uzytkownik user) {
        System.out.println("Login: " + user.getLogin());
        System.out.println("Hasło: " + user.getPassword());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        Uzytkownik newUser = new Uzytkownik(user.getLogin(), hashedPassword, "USER");
        personDataBase.uzytkownikRepository.save(newUser);
    }

    @RequestMapping(value = "/register1", method = RequestMethod.POST)
    void registerUser(@RequestBody User toAdd) {
        System.out.println("CHUJ");
        if (personDataBase.userRepository.findByUsername(toAdd.getUsername()).isPresent()) {
            return;
        }

        toAdd.setPassword(passwordEncoder.encode(toAdd.getPassword()));
        toAdd.setEnabled(true);
        User addedUser = personDataBase.userRepository.save(toAdd);

        Authority addAuthority = new Authority(toAdd.getUsername(), "ROLE_USER");
        personDataBase.authorityRepository.save(addAuthority);

    }

    // @GetMapping()
    // public String showLoginPage() {
    // return "login";
    // }

    // // comment
    // @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    // public String addComment(Comment comment) {

    // return comment;
    // }

    // test
}
