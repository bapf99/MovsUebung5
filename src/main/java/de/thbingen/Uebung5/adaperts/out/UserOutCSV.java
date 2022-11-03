package de.thbingen.Uebung5.adaperts.out;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import de.thbingen.Uebung5.ports.in.User;
import de.thbingen.Uebung5.ports.out.UserOutPort;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Repository
public class UserOutCSV implements UserOutPort {

    public int storeUserToCSV(User user){

        List<User> users = getAllUsers();
        if(users.size() > user.getId()){
            System.out.println("Error: ID already in use!");
            return -1;
        }else{
            try {
                File csvFile = new File("users.csv");
                FileWriter csvWriter = new FileWriter(csvFile);
                CSVWriter writer = new CSVWriter(csvWriter);
                String[] userData = {String.valueOf(user.getId()), user.getName()};
                writer.writeNext(userData);

                writer.close();
            }catch(IOException io){
                System.out.println("CSV Write Error");
                io.printStackTrace();
            }
        }

        return 0;
    }

    public List<User> getAllUsers() {
        try {
            List<User> users;
            CSVReader csvReader = new CSVReader(new FileReader("users.csv"));

        }catch(IOException io){
            io.printStackTrace();
        }
        return null;
    }

    public User finfUserById(int id) {
        return null;
    }

    public Boolean deleteUserById(int id) {
        return null;
    }

    public long updateUserById(int id) {
        return 0;
    }

    public long replaceUserById(int id) {
        return 0;
    }
}
