package com.example.demo.servingwebcontent;

import com.example.demo.Model.Authorization;
import com.example.demo.Model.Post;
import com.example.demo.repo.PostReposytoty;
import com.example.demo.repo.UserAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class MainControler {

    @Autowired
    private UserAuthorization userAuthorization;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/registri")
    public String register(Model model) {
        return "registri";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userLogin, @RequestParam String userPassword,Model model) {
        Iterable<Authorization> authorizations = userAuthorization.findAll();
        Iterator iterator = authorizations.iterator();
        ArrayList<Authorization> arr = new ArrayList<>();
        while (iterator.hasNext()) {
            arr.add((Authorization) iterator.next());
        }
        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).getLogin().isEmpty() && arr.get(i).getLogin().equals(userLogin)&&!arr.get(i).getPassword().isEmpty() && arr.get(i).getPassword().equals(userPassword)) {
                return "redirect:/blog";
            }
        }

        return "login";
    }

    @PostMapping("/registri")
    public String register(@RequestParam String userLogin, @RequestParam String userPassword, Model model) {
        Authorization authorization = new Authorization(userLogin, userPassword);
        userAuthorization.save(authorization);
        return "redirect:/login";
    }

}