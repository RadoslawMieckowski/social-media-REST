package com.example.demo.service;

import com.example.demo.beans.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDAOService {
private static List<User> users = new ArrayList<>();

    static {
        users.add(
                User.builder()
                        .id(1)
                        .name("Radek")
                        .birthDate(LocalDate.now().minusYears(25))
                        .build()
        );
        users.add(
                User.builder()
                        .id(2)
                        .name("Konrad")
                        .birthDate(LocalDate.now().minusYears(35))
                        .build()
        );
        users.add(
                User.builder()
                        .id(3)
                        .name("Marek")
                        .birthDate(LocalDate.now().minusYears(65))
                        .build()
        );
    }
    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int index) {
        //return users.get(index);
        Predicate<? super User> predicate = user -> user.getId().equals(index);      //functional style
        return users.stream().filter(predicate).findFirst().get();
    }
}
