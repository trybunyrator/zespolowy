package controllers;

//To jest kontroler do przykładu z baza osób

import common.TPerson;
import controllers.models.Category;
import controllers.models.Comment;
import controllers.models.Post;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @Autowired
    MyDataBase personDataBase;

    // testowy endpoint
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String systemInfo() {

        return "Testowy serwis webowy";
    }

    // wyświetlanie wszystkich kategorii
    @RequestMapping(value = "/getAllCategory", method = RequestMethod.GET)
    public List<Category> getAllCategory() {
        System.out.println(personDataBase.categoryRepository.findAll());
        return personDataBase.categoryRepository.findAll();
    }

    // wyświetlanie komentarzy do danego id posta - UWAGA - NIE PODKOMENTARZY
    // (IDPARENT NULL)
    @RequestMapping(value = "/commentsPost/{id}", method = RequestMethod.GET)
    public List<Comment> getCommentsPost(@PathVariable("id") Post id) {
        System.out.println(personDataBase.commentRepository.findAllByIdPostIsAndIdParentNull(id));
        return personDataBase.commentRepository.findAllByIdPostIsAndIdParentNull(id);
    }

    // wyświetlanie komentarzy do DANEGO KOMENTARZA
    @RequestMapping(value = "/commentsComment/{id}", method = RequestMethod.GET)
    public String getCommentsPost(@PathVariable("id") Comment id) {
        System.out.println(personDataBase.commentRepository.findAllByIdParentIs(id));
        return "commentsComment/{id}";
    }

    // wyświetlanie wszystkich postów w danej kategorii
    @RequestMapping(value = "/getAllPostsInCategory/{id}", method = RequestMethod.GET)
    public String getAllPostsCategory(@PathVariable("id") Category id) {
        System.out.println(personDataBase.postRepository.findAllByIdCategoryIs(id));
        return "getAllPostsInCategory/{id}";
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

    // // comment
    // @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    // public String addComment(Comment comment) {

    // return comment;
    // }

    // test
}
