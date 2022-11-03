package de.thbingen.Uebung5.adaperts.in;

import de.thbingen.Uebung5.ports.in.RESTPort;
import de.thbingen.Uebung5.ports.in.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class RESTController {

    @Autowired
    private RESTPort restPort;

    private static int lastId = 0;

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

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestParam(required = true) String name){
        int id = lastId;
        lastId++;
        restPort.addUser(id, name);
        System.out.println("Added User: " + name + " ID: " + id);
        return "Added User: " + name + " ID: " + id;
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity deleteUserById(@RequestBody int id){
        HttpStatus status = restPort.deleteUserById(id) ? HttpStatus.valueOf(204) : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(status);
    }

    @PatchMapping(value = "/users")
    public ResponseEntity updateWithUserId(@RequestBody User user){
        int id = restPort.updateUserById(user);

        return id != -1 ? ResponseEntity.ok("Successfully updated the user") : ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/users")
    public ResponseEntity replaceUserWithId(@RequestBody User user) {
        int id = restPort.replaceUserById(user);

        return id != -1 ? ResponseEntity.ok("Successfully put the user") : ResponseEntity.badRequest().build();
    }

}
