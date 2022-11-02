package de.thbingen.Uebung5.ports.in;

import java.util.List;
import de.thbingen.Uebung5.ports.in.User;

public interface RESTPort {

    public int addUser(int id, String name);

    public List<User> getAllUsers();

    public User getUserById(int userId);

    public User getUserByName(String username);

    public Boolean deleteUserById(int id);

    public int updateUserById(User user);

    public int replaceUserById(User user);

}
