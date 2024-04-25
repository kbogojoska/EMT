package mk.ukim.finki.emt.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Country country;

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
