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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.StreamTokenizer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

@Controller
public class Controler {
    @Autowired
    private PostReposytoty postReposytoty;

    @Autowired
    private UserAuthorization userAuthorization;

    @GetMapping("/blog")
    public String blogMen(Model model) {
        Iterable<Post> posts = postReposytoty.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        postReposytoty.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postReposytoty.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postReposytoty.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postReposytoty.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postReposytoty.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostEdit(@PathVariable(value = "id") long id,@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postReposytoty.findById(id).orElseThrow();
        post.setTitle(title);
        post.setTitle(anons);
        post.setTitle(full_text);
        postReposytoty.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") long id, Model model) {
        postReposytoty.deleteById(id);
        return "redirect:/blog";
    }


}
