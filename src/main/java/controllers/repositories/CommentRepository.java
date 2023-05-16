package controllers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import controllers.models.Comment;
import controllers.models.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Comment findById();

    List<Comment> findAllByIdPost(Post id);

    // LISTA KOMENTARZY DO POSTA
    List<Comment> findAllByIdPostIsAndIdParentNull(Post id);

    // sprawdzanie czy do danego posta są komentarze
    boolean existsByIdPost(Post id);

    // LISTA KOMENTARZY DO KOMENTARZA
    List<Comment> findAllByIdParentIs(Comment id);

    // sprawdzanie czy istnieje rekord o podanym id
    boolean existsById(Long id);
}
