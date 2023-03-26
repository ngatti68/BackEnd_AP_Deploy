package com.miportfolioweb.backend.controller;

import com.miportfolioweb.backend.DTO.BannerDTO;
import com.miportfolioweb.backend.DTO.Mensaje;
import com.miportfolioweb.backend.entity.Banner;
import com.miportfolioweb.backend.service.BannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/banner")
@CrossOrigin(origins = "http://localhost:4200")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/lista")
    public ResponseEntity<List<Banner>> list(){
        List<Banner> list = bannerService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getById(@PathVariable("id")int id){
        if(!bannerService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.BAD_REQUEST);
        }

        Banner banner = bannerService.getOne(id).orElse(null);
        return new ResponseEntity<>(banner, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody BannerDTO bannerDTO){
        Banner banner = new Banner(bannerDTO.getUrlimg());
        bannerService.save(banner);

        return new ResponseEntity<>(new Mensaje("Banner agregado satisfactoriamente."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody BannerDTO bannerDTO){
        if(!bannerService.existsById(id)){
            return new ResponseEntity<>(new Mensaje("Este ID no existe."), HttpStatus.NOT_FOUND);
        }
        if(bannerService.existsByUrlimg(bannerDTO.getUrlimg()) && bannerService.getByUrlimg(bannerDTO.getUrlimg()).get().getId() != id){
            return new ResponseEntity<>(new Mensaje("Este nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(bannerDTO.getUrlimg())){
            return new ResponseEntity<>(new Mensaje("Este campo no puede estar vacío."), HttpStatus.BAD_REQUEST);
        }

        Banner banner = bannerService.getOne(id).orElse(null);

        assert banner != null;
        banner.setUrlimg(bannerDTO.getUrlimg());

        bannerService.save(banner);

        return new ResponseEntity<>(new Mensaje("Banner actualizado correctamente."), HttpStatus.OK);
    }
}
