package dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class EmployeeDto {
    private String name;
    private String country;
    private String created;

    @Override
    public String toString() {
        return String.format("[Employee INFO]: Name: %s; Country: %s; Created: %s", name, country, created);
    }
}
