package uz.pdp.apppcmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Basket {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

}
