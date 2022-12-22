package com.example.demo.service;

import com.example.demo.beans.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
}
