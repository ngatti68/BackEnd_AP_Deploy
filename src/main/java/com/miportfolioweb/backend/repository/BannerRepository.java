package com.miportfolioweb.backend.repository;

import com.miportfolioweb.backend.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

    public Optional<Banner> findByUrlimg(String urlimg);
    public boolean existsByUrlimg(String urlimg);

}
