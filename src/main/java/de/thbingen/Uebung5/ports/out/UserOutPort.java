package de.thbingen.Uebung5.ports.out;

import de.thbingen.Uebung5.ports.in.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface UserOutPort {

    int storeUserToCSV(User user) throws IOException;

    List<User> getAllUsers();

    User findUserById(int id);

    User findUserByName(String name);

    Boolean deleteUserById(int id) throws IOException;

    int updateUser(User user) throws IOException;

    int replaceUser(User user) throws IOException;

}
