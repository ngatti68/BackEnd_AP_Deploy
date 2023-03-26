package com.miportfolioweb.backend.service;

import com.miportfolioweb.backend.entity.Banner;
import com.miportfolioweb.backend.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BannerService {

    @Autowired
    BannerRepository bannerRepository;

    public List<Banner> list(){
        return bannerRepository.findAll();
    }

    public Optional<Banner> getOne(int id){
        return bannerRepository.findById(id);
    }

    public Optional<Banner> getByUrlimg(String urlimg){
        return bannerRepository.findByUrlimg(urlimg);
    }

    public void save(Banner banner){
        bannerRepository.save(banner);
    }

    public void delete(int id){
        bannerRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return bannerRepository.existsById(id);
    }

    public boolean existsByUrlimg(String urlimg){
        return bannerRepository.existsByUrlimg(urlimg);
    }

}
