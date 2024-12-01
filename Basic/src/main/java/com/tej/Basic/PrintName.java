package com.tej.Basic;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PrintName {

    // Stored the data into the map using HashMap
    private Map<Long, UserName> userName = new HashMap<>();

    //this is the get request to fetch the data from the backend
    @GetMapping()
    public List<UserName> getAllUsers(){
        return new ArrayList<>(userName.values());
    }

    //Add user into the local data (map)
    @PostMapping()
    public boolean addUser(@RequestBody UserName entity){
        userName.put(entity.getId(),entity);
        return true;
    }

    //search user into the local db(Map)
    @GetMapping("id/{id}")
    public UserName getDataById(@PathVariable Long id){
        return userName.get(id);
    }

// update user into the local DB (Map)
    @PutMapping("id/{id}")
    public UserName updateDataById(@PathVariable Long id, @RequestBody UserName myEntity){
        return userName.put(id, myEntity);
    }

    // delete user into the LOCAL DB (Map)
    @DeleteMapping("id/{id}")
    public UserName deleteById(@PathVariable  Long id){
        return userName.remove(id);
    }



}
