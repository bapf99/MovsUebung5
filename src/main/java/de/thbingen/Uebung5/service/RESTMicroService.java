package de.thbingen.Uebung5.service;

import de.thbingen.Uebung5.ports.in.RESTPort;
import de.thbingen.Uebung5.ports.in.User;
import de.thbingen.Uebung5.ports.out.UserOutPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RESTMicroService implements RESTPort {

    @Autowired
    private UserOutPort userOutPort;

    public int addUser(int id, String name) {
        return 0;
    }

    public List<User> getAllUsers() {
        return null;
    }

    public User getUserById(int userId) {
        return null;
    }

    public User getUserByName(String username) {
        return null;
    }

    public Boolean deleteUserById(int id) {
        return null;
    }

    public int updateUserById(User user) {
        return 0;
    }

    public int replaceUserById(User user) {
        return 0;
    }
}
