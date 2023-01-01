package org.greytcoders.rent_a_bs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.greytcoders.rent_a_bs.models.User;
import org.greytcoders.rent_a_bs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            LOGGER.info("operation='createUser', create user='{}'", user);

            User newUser = userService.insert(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Error error) {
            error.getMessage();
        }

        return null;
    }

    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUser() {

        LOGGER.info("operation='getAllUser'");

        Optional<Iterable<User>> allUsers = userService.getAll();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(allUsers.get(), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id, @RequestBody String token) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            if (userService.getUserById(id).get().getToken() == null ||
                    !userService.getUserById(id).get().getToken().equals(objectMapper.readValue(token, User.class).getToken())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (JsonProcessingException exception) {
            LOGGER.info("ERROR PARSING JSON");
            exception.printStackTrace();
        }

        LOGGER.info("operation='getUserById', id='{}'", id);

        Optional<User> opUser = userService.getUserById(id);

        if (opUser.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(opUser.get(), HttpStatus.OK);
        }
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public String deleteID(@PathVariable Long id) {
        LOGGER.info("operation='deleteUserById', id='{}'", id);

        userService.deleteId(id);
        return "Customer deleted with success";

    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<User> userLogin(@RequestBody User body) {

        try {
            for (User user : userService.getAll().get()) {

            if (user.getPassword().equals(body.getPassword()) && user.getEmail().equals(body.getEmail())) {
                    Random rand = new Random();
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < 8; i++) {
                        sb.append((char) ('a' + rand.nextInt(26)));
                    }

                    String token = sb.toString();
                    user.setToken(token);
                    userService.insert(user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
        } catch (Error e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/logout")
    public ResponseEntity<String> userLogout(@RequestBody Long id) {

        if (userService.getUserById(id).get().getToken() != null) {
            userService.getUserById(id).get().setToken(null);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    }

}
