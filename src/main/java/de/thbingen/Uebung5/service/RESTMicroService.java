package de.thbingen.Uebung5.service;

import de.thbingen.Uebung5.ports.in.RESTPort;
import de.thbingen.Uebung5.ports.in.User;
import de.thbingen.Uebung5.ports.out.UserOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RESTMicroService implements RESTPort {

    @Autowired
    private UserOutPort userOutPort;

    public int addUser(int id, String name) throws IOException {
        User newUser = new User();
        newUser.setId(id);
        newUser.setName(name);
        System.out.println("Service created User");
        return userOutPort.storeUserToCSV(newUser);
    }

    public List<User> getAllUsers() {
        return userOutPort.getAllUsers();
    }

    public User getUserById(int userId) {
        return userOutPort.findUserById(userId);
    }

    public User getUserByName(String username) {
        return userOutPort.findUserByName(username);
    }

    public Boolean deleteUserById(int id) throws IOException {
        return userOutPort.deleteUserById(id);
    }

    public int updateUser(User user) throws IOException {
        return userOutPort.updateUser(user);
    }

    public int replaceUser(User user) throws IOException {
        return userOutPort.replaceUser(user);
    }
}
