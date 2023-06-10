package controllers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import controllers.models.Uzytkownik;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Long>{
  Uzytkownik findByLogin(String login);
}
