package controllers.models;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@CrossOrigin
@Entity
@Table(name = "Uzytkownik")
public class Uzytkownik {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long idUser;

  private String login;

  private String password;
  private String role;

  public Uzytkownik() {
  }

  public Uzytkownik(String login, String password, String role) {
    this.login = login;
    this.password = password;
    this.role = role;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "User [idUser=" + idUser + ", login=" + login + ", password=" + password + ", role=" + role + "]";
  }

}
