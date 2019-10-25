package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    AdminRepository adminRepository;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("users", new User());

        return "homepage";
    }

    @GetMapping("/homepage")
    public String userForm(Model model) {
        model.addAttribute("users", new User());

        return "homepage";

    }

    @GetMapping("/transaction")
    public String transaction(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("user", userRepository.findAll());
        return "transaction";
    }

    @GetMapping("/album")
    public String album(Model model) {
        model.addAttribute("album", new Album());
        model.addAttribute("transaction", transactionRepository.findAll());
        model.addAttribute("user", userRepository.findAll());
        return "album";
    }

    @PostMapping("/processuser")
    public String processUser(@Valid User user,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "homepage";
        }
        userRepository.save(user);
        return "redirect:/album";
    }

    @PostMapping("/album")
    public String processAlbum(@Valid Album album,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "album";


        }
        albumRepository.save(album);
        return "redirect:/transaction";
    }

    @PostMapping("/transaction")
    public String processTransaction(@Valid Transaction transaction,
                                     BindingResult result) {
        if (result.hasErrors()) {
            return "transaction";
        }
        transactionRepository.save(transaction);
        return "redirect:/";
    }

    // ========new code=============================

    @GetMapping("/adminform")
    public String adminform(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("user", userRepository.findAll());
        return "adminform";


    }


    @PostMapping("/adminform")
    public String processAdmin(@Valid Admin admin,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "adminform";
        }
        adminRepository.save(admin);
        return "adminform";
    }

    @RequestMapping("/deleteperson/{id}")


    public String delAlbum(@PathVariable("id") long id) {
        albumRepository.deleteById(id);
        return "redirect:/";


    }


}



