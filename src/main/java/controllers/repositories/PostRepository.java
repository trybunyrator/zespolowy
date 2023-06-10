package controllers.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import controllers.models.Category;
import controllers.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByIdCategoryIs(Category id);

    Post findById(long id);

}
