package ru.taranov.homeSale.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.taranov.homeSale.entity.Account;
import ru.taranov.homeSale.entity.Advert;

import java.util.List;

public interface AdvertService {

    public List<Advert> findAll();

    public void save(@AuthenticationPrincipal Account user, Advert theAdvert);

    public Advert findById(int theId);

    public List<Advert> findByTitle(String title);

    public void deleteById(int theId);
}
