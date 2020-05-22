package ru.taranov.homeSale.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taranov.homeSale.entity.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
