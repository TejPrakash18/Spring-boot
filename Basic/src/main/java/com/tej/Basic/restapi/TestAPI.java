package com.tej.Basic.restapi;


import com.tej.Basic.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
//@RequestMapping("/api")

@RequestMapping("/users")
public class TestAPI {

//    private final HashMap<Long, User> users = new HashMap<>();
//    private Long userId = 1l;
//
//// get the all users
//    @GetMapping("/users")
//    public List<User> getAllUsers(){
//        return new ArrayList<>(users.values());
//    }
//
//    // create a users
//    @PostMapping("/create")
//    public String create(@RequestBody User user){
//        user.setId(userId++);
//        users.put(user.getId(),user);
//        return "Successful";
//    }
//
//    // search the user
//
//    @GetMapping("/{id}")
//    public Object getById(@PathVariable Long id){
//        User user = users.get(id);
//        if(user == null){
//            return null;
//        }
//        return user;
//    }
//
//    //update the user
//
//    @PutMapping("/{id}")
//    public String updateUser(@PathVariable Long id, @RequestBody User user){
//        if (!users.containsKey(id)){
//            return "Not found";
//        }
//        user.setId(id);
//        users.put(id, user);
//        return "update successful";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable Long id){
//        if (!users.containsKey(id)){
//            return "User not found";
//        }
//        users.remove(id);
//        return "Delete User";
//    }
//



    private final HashMap<Long, User> users = new HashMap<>();
    private Long userIdCounter = 1l;

    //Get All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(new ArrayList<>(users.values()));
    }

    // Get a single user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(userIdCounter++);
        users.put(user.getId(), user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!users.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        user.setId(id);
        users.put(id, user);
        return ResponseEntity.ok(user);
    }

    // Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!users.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        users.remove(id);
        return ResponseEntity.noContent().build();
    }

}
