package org.example.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerEntity {
    @Id
    private String uuid;
    private String documentNumber;
    private String name;
    private String phone;

    public CustomerEntity(String documentNumber, String name, String phone) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.phone = phone;
    }
}
