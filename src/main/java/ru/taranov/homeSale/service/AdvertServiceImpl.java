package ru.taranov.homeSale.service;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import ru.taranov.homeSale.dao.AdvertRepository;
import ru.taranov.homeSale.entity.Account;
import ru.taranov.homeSale.entity.Advert;
import ru.taranov.homeSale.controller.AdvertNotFoundException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertServiceImpl implements AdvertService {

    private AdvertRepository advertRepository;

    public AdvertServiceImpl(AdvertRepository theAdvertRepository) {
        this.advertRepository = theAdvertRepository;
    }

    @Override
    public List<Advert> findAll() {
        return advertRepository.findAll();
    }

    @Override
    public void save(@AuthenticationPrincipal Account user,
                     Advert advert) {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));
        advert.setAuthor(user);
        advert.setAdded(timestamp);

        advertRepository.save(advert);
    }

    @Override
    public Advert findById(int theId) {
        Optional<Advert> result = advertRepository.findById(theId);

        Advert advert = null;
        if (result.isPresent()) {
            advert = result.get();
        } else {
            throw new AdvertNotFoundException("Did not find advert id - " + theId);
        }
        return advert;
    }

    @Override
    public List<Advert> findByTitle(String title) {
        return advertRepository.findAllByTitle(title);
    }

    @Override
    public void deleteById(int theId) {
        advertRepository.deleteById(theId);
    }
}