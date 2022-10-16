package model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
