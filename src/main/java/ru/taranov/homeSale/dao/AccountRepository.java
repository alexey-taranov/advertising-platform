package ru.taranov.homeSale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taranov.homeSale.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByUsername(String username);
}
