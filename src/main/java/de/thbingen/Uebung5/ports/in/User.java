package de.thbingen.Uebung5.ports.in;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class User {

    @CsvBindByPosition(position = 0)
    int id;
    @CsvBindByPosition(position = 1)
    String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(User user){
        if(user.getId() == this.id && user.getName() == this.name){
            return true;
        }else{
            return false;
        }
    }
}
