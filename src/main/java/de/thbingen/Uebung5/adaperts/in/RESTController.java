package de.thbingen.Uebung5.adaperts.in;

import de.thbingen.Uebung5.ports.in.RESTPort;
import de.thbingen.Uebung5.ports.in.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class RESTController {

    @Autowired
    private RESTPort restPort;

    private static int lastId = 0;

    @GetMapping(value = "/users/all")
    public List<User> getAllUsers(){
        return restPort.getAllUsers();
    }

    @GetMapping(value = "/users")
    public User getUserByName(@RequestParam(required = false) String name){
        User user = null;
        if(name == null){
            System.out.println("Fehler, kein Name angegeben!");
        }else{
            // Send one specific user
            user = restPort.getUserByName(name);
        }

        return user;
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable int id){
        return restPort.getUserById(id);
    }

    @PostMapping(value = "/users")
    public String addUser(@RequestParam(required = true) String name){
        int id = lastId;
        int response = 1;
        String state;
        lastId++;
        try {
            response = restPort.addUser(id, name);
        }catch(IOException io){
            io.printStackTrace();
        }
        if(response == -1){
            state = "User was not added!";
        }else{
            state = "Added User: " + name + " ID: " + id;
        }

        return state;
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable int id){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        try {
            status = restPort.deleteUserById(id) ? HttpStatus.valueOf(204) : HttpStatus.BAD_REQUEST;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(status);
    }

    @PatchMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@RequestBody User user){
        int id = -1;
        try {
            id = restPort.updateUser(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return id != -1 ? ResponseEntity.ok("Successfully updated the user") : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity replaceUser(@RequestBody User user) {
        int id = -1;
        try {
            id = restPort.replaceUser(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return id != -1 ? ResponseEntity.ok("Successfully put the user") : ResponseEntity.badRequest().build();
    }

}
