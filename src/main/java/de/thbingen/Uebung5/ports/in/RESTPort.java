package de.thbingen.Uebung5.ports.in;

import java.io.IOException;
import java.util.List;
import de.thbingen.Uebung5.ports.in.User;

public interface RESTPort {

    public int addUser(int id, String name) throws IOException;

    public List<User> getAllUsers();

    public User getUserById(int userId);

    public User getUserByName(String username);

    public Boolean deleteUserById(int id) throws IOException;

    public int updateUser(User user) throws IOException;

    public int replaceUser(User user) throws IOException;

}
