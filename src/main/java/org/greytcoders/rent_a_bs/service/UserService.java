package org.greytcoders.rent_a_bs.service;

import org.greytcoders.rent_a_bs.models.User;
import org.greytcoders.rent_a_bs.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public User insert(User user) {
        User newUser = userRepo.save(user);
        return newUser;
    }

    public Optional<Iterable<User>> getAll() {
        return Optional.of(userRepo.findAll());
    }
    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public void deleteId(Long id){
        userRepo.deleteById(id);
    }

}
