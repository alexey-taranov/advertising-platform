package ru.taranov.homeSale.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.taranov.homeSale.entity.Account;
import ru.taranov.homeSale.entity.Advert;
import ru.taranov.homeSale.service.AdvertService;
import ru.taranov.homeSale.service.FileUploadService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/adverts")
public class AdvertController {

    private AdvertService advertService;
    private FileUploadService fileUploadService;

    public AdvertController(AdvertService advertService, FileUploadService fileUploadService) {
        this.advertService = advertService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/list")
    public String listAdverts(Model theModel) {
        List<Advert> theAdverts = advertService.findAll();
        theModel.addAttribute("adverts", theAdverts);

        return "adverts/list-adverts";
    }

    @RequestMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Advert theAdvert = new Advert();
        theModel.addAttribute("advert", theAdvert);

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
    public String saveAdvert(@RequestParam("file") MultipartFile file,
                             @AuthenticationPrincipal Account user,
                             @Valid @ModelAttribute("advert") Advert theAdvert,
                             BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return "adverts/advert-form";
        } else {
            String filepath = fileUploadService.upload(file);
            theAdvert.setFilename(filepath);

            advertService.save(user, theAdvert);
        }

        return "redirect:/adverts/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("advertId") int theId) {
        advertService.deleteById(theId);

        return "redirect:/adverts/list";
    }
}
