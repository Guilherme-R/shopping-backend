package gsobrinho.shoppingbackend.api.dto;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
}
