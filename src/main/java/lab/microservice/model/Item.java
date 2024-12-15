package lab.microservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "items")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "info", nullable = false)
    private String info;

}
