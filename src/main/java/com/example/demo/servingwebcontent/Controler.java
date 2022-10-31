package com.example.demo.servingwebcontent;

import com.example.demo.Model.Post;
import com.example.demo.repo.PostReposytoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controler {
    @Autowired
    private PostReposytoty postReposytoty;

    @GetMapping("/blog")
    public String blogMen(Model model) {
        Iterable<Post> posts = postReposytoty.findAll();
        model.addAttribute("posts",posts);
        return "blog-men";
    }

}
