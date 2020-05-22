package ru.taranov.homeSale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.taranov.homeSale.dao.AccountRepository;
import ru.taranov.homeSale.entity.Account;
import ru.taranov.homeSale.entity.Role;

import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String addUser(Account user, Map<String, Object> model) {
        Account userFromDb = accountRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        accountRepository.save(user);

        return "redirect:/login";
    }
}
