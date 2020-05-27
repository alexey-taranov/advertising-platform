package ru.taranov.homeSale.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taranov.homeSale.entity.Advert;

import java.util.List;

public interface AdvertRepository extends JpaRepository<Advert, Integer> {

    List<Advert> findAllByTitle(String title);
}
