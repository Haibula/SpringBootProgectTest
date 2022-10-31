package com.example.demo.repo;

import com.example.demo.Model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostReposytoty extends CrudRepository<Post,Long> {
}
