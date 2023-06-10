package controllers.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import controllers.models.Uzytkownik;
import controllers.repositories.UzytkownikRepository;

@Service
public class DatabaseUserDetailsService implements AuthenticationProvider {

    private final UzytkownikRepository userRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public DatabaseUserDetailsService(UzytkownikRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @Override
    // public UserDetails loadUserByUsername(String username) throws
    // UsernameNotFoundException {
    // Uzytkownik uzytkownik = userRepository.findByLogin(username);
    // if (uzytkownik == null) {
    // throw new UsernameNotFoundException("Użytkownik o podanym loginie nie
    // istnieje: " + username);
    // }
    // //String storedPassword = uzytkownik.getPassword();
    ////
    // //// Sprawdzenie zgodności hasła wprowadzonego przez użytkownika z hasłem w
    //// bazie danych
    // //String inputPassword = request.getParameter("password");
    // //if (!inputPassword.equals(storedPassword)) {
    // // throw new BadCredentialsException("Nieprawidłowe hasło");
    // //}
    //
    // //return new User(uzytkownik.getLogin(), storedPassword,
    //// Collections.singleton(new SimpleGrantedAuthority("USER")));
    // return new User(uzytkownik.getLogin(), uzytkownik.getPassword(),
    //// Collections.singleton(new SimpleGrantedAuthority("USER")));
    // }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        final String email = authentication.getName(); // z form logowania pobiera login
        final String password = authentication.getCredentials().toString(); // z form logowania pobiera hasło
        System.out.println(passwordEncoder.encode(password));
        Uzytkownik uzytkownik = userRepository.findByLogin(email); // szuka użytkownika z podanym w logowaniu emailem
        if (uzytkownik == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (passwordEncoder.matches(password, uzytkownik.getPassword())) { // jak jest to sprawdza czy hasła się
                                                                           // zgadzają
            List<GrantedAuthority> authorities = new ArrayList<>(); // tworzy liste typu GrantedAuthority
            authorities.add(new SimpleGrantedAuthority(uzytkownik.getRole())); // dodaje do listy uprawnień tego usera
            return new UsernamePasswordAuthenticationToken(email, password, authorities); // zwraca nam token po którym
                                                                                          // się autoryzuje
        } else {
            throw new BadCredentialsException("Login error!"); // jeśli znalazło usera ale hasło się nie zgadza to
                                                               // wywala wyjątek
        }
        // throw new UnsupportedOperationException("Unimplemented method
        // 'authenticate'");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return true;
        // throw new UnsupportedOperationException("Unimplemented method 'supports'");
    }
}
