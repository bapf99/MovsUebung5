package de.thbingen.Uebung5.adaperts.out;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import de.thbingen.Uebung5.ports.in.User;
import de.thbingen.Uebung5.ports.out.UserOutPort;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserOutCSV implements UserOutPort {

    String filePath = "C:\\Users\\basti\\Desktop\\users.csv";
    public int storeUserToCSV(User user){

        List<User> users = getAllUsers();
        if(users != null && users.contains(user)){
            System.out.println("Error: ID already in use!");
            return -1;
        }else{
            try {
                File csvFile = new File(filePath);
                FileWriter csvWriter = new FileWriter(csvFile);
                CSVWriter writer = new CSVWriter(csvWriter);

                List<String[]> data = new ArrayList<String[]>();
                for(int i = 0; i < users.size(); i++){
                    data.add(new String[] {users.get(i).getId()+"", users.get(i).getName()});
                }
                data.add(new String[] {user.getId()+"", user.getName()});
                writer.writeAll(data);

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
            File file = new File(filePath);
            FileReader reader = new FileReader(file);
            List<User> users = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)
                    .build()
                    .parse();

            reader.close();
            return users;
        }catch(IOException io){
            io.printStackTrace();
        }
        return null;
    }

    public User findUserById(int id) {
        List<User> users = getAllUsers();
        User searchedUser = null;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == id)
                searchedUser = users.get(i);
        }

        if(searchedUser != null){
            System.out.println("Found user by id!");
        }

        return searchedUser;
    }

    public User findUserByName(String name){
        List<User> users = getAllUsers();
        User searchedUser = null;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getName().equals(name))
                searchedUser = users.get(i);
        }

        if(searchedUser != null){
            System.out.println("Found user by name!");
        }

        return searchedUser;
    }

    public Boolean deleteUserById(int id) throws IOException {
        List<User> users = getAllUsers();
        List<String[]> data = new ArrayList<String[]>();
        boolean foundUser = false;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == id){
                foundUser = true;
                System.out.println("Deleted User");
            }else{
                data.add(new String[] {users.get(i).getId()+"", users.get(i).getName()});
            }
        }

        if(foundUser){
            File csvFile = new File(filePath);
            FileWriter csvWriter = new FileWriter(csvFile);
            CSVWriter writer = new CSVWriter(csvWriter);

            writer.writeAll(data);
            writer.close();

            return true;
        }else{
            return false;
        }
    }

    public int updateUser(User user) throws IOException {

        List<User> users = getAllUsers();
        List<String[]> data = new ArrayList<String[]>();
        boolean foundUser = false;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == user.getId()) {
                users.get(i).setName(user.getName());
                data.add(new String[] {users.get(i).getId()+"", users.get(i).getName()});
                System.out.println("Updated User");
                foundUser = true;
            }else{
                data.add(new String[] {users.get(i).getId()+"", users.get(i).getName()});
            }
        }

        if(foundUser){
            File csvFile = new File(filePath);
            FileWriter csvWriter = new FileWriter(csvFile);
            CSVWriter writer = new CSVWriter(csvWriter);

            writer.writeAll(data);
            writer.close();

            return 0;
        }else{
            return -1;
        }
    }

    public int replaceUser(User user) throws IOException {

        List<User> users = getAllUsers();
        List<String[]> data = new ArrayList<String[]>();
        boolean foundUser = false;

        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getId() == user.getId()) {
                data.add(new String[] {user.getId()+"", user.getName()});
                System.out.println("Replaced User");
                foundUser = true;
            }else{
                data.add(new String[] {users.get(i).getId()+"", users.get(i).getName()});
            }
        }

        if(foundUser){
            File csvFile = new File(filePath);
            FileWriter csvWriter = new FileWriter(csvFile);
            CSVWriter writer = new CSVWriter(csvWriter);

            writer.writeAll(data);
            writer.close();

            return 0;
        }else{
            return -1;
        }
    }
}
