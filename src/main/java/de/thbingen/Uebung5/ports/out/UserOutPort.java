package de.thbingen.Uebung5.ports.out;

import de.thbingen.Uebung5.ports.in.User;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public interface UserOutPort {

    int storeUserToCSV(User user) throws IOException;

    List<User> getAllUsers();

    User finfUserById(int id);

    Boolean deleteUserById(int id);

    long updateUserById(int id);

    long replaceUserById(int id);

}
