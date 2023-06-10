package controllers;

import common.TPerson;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Autowired
    MyDataBase personDataBase;

    @Override
    public void run(String... args) {

        // Wstawienie osob do bazy
        // personDataBase.addPerson("jbarski@gmail.com", "Janusz", "Barski", 45);
        // personDataBase.addPerson("jwokulski@gmail.com", "Janusz", "Wokulski", 32);
        // personDataBase.addPerson("awazna@gmail.com", "Anna", "Ważna", 24);

        personDataBase.addCategory();

        // ----------------------------------------------------

        // System.out.println("Osoby wszystkie...");
        // ArrayList<TPerson> personList = personDataBase.getPersonList();
        // for (int i = 0; i < personList.size(); i++) {
        // System.out.println(personList.get(i).toString());
        // }
        // System.out.println("=====================================");

        // ----------------------------------------------------

        // Demonstracja usuwania osob w oparciu o adres email
        // personDataBase.deletePerson("jwokulski@gmail.com");

        // System.out.println("Osoby po usunieciu...");
        // personList = personDataBase.getPersonList();
        // for (int i = 0; i < personList.size(); i++) {
        // System.out.println(personList.get(i).toString());
        // }
        // System.out.println("=====================================");

        // ---------------------------------------------------

    }
}
