package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
public class Employee {
    private long id;
    private String name;
    private String country;
    private LocalDateTime created;

    public Employee(@NonNull String name, String country) {
        this.name = name;
        this.country = country;
        this.created = LocalDateTime.now();
    }
}
