package com.example.demo.repo;

import com.example.demo.Model.Authorization;
import org.springframework.data.repository.CrudRepository;

public interface UserAuthorization extends CrudRepository<Authorization,Long> {
}
