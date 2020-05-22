package ru.taranov.homeSale.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ru.taranov.homeSale.entity.Account;
import ru.taranov.homeSale.entity.Advert;
import ru.taranov.homeSale.service.AdvertService;


import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/adverts")
public class AdvertController {

    @Value("${upload.path}")
    private String uploadPath;

    private AdvertService advertService;

    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @GetMapping("/list")
    public String listAdverts(Model theModel) {
        List<Advert> theAdverts = advertService.findAll();
        theModel.addAttribute("adverts", theAdverts);

        return "adverts/list-adverts";
    }

    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(MultipartFile file, Model theModel) throws IOException {

        Advert theAdvert = new Advert();
        theModel.addAttribute("advert", theAdvert);

//        Photo filemode = new Photo(file.getOriginalFilename(), file.getContentType(), file.getBytes());
//        photoRepository.save(filemode);

        return "adverts/advert-form";
    }

//    @GetMapping("/adverts/{advertId}")
//    public Advert getAdvert(@PathVariable int advertId) {
//        Advert theAdvert = advertService.getAdvert(advertId);
//
//        if (theAdvert == null) {
//            throw new AdvertNotFoundException("Advert id not found - " + advertId);
//        }
//
//        return theAdvert;
//    }

    @GetMapping("/search")
    public String search(@RequestParam("advertTitle") String theTitle, Model theModel) {
        List<Advert> theAdverts = advertService.findByTitle(theTitle);
        theModel.addAttribute("adverts", theAdverts);

        return "adverts/list-adverts";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("advertId") int theId,
                                    Model theModel) {
        Advert theAdvert = advertService.findById(theId);
        theModel.addAttribute("advert", theAdvert);

        return "adverts/advert-form";
    }

    @PostMapping("/save")
    public String saveAdvert(@AuthenticationPrincipal Account user,@ModelAttribute("advert") Advert theAdvert) throws Exception {

        advertService.save(user, theAdvert);

        return "redirect:/adverts/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("advertId") int theId) {
        advertService.deleteById(theId);

        return "redirect:/adverts/list";
    }
}
