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
}
