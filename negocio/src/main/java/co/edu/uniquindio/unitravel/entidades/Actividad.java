package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Actividad implements Serializable {
    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;

    @ToString.Include
    @Column(length = 50, nullable = false)
    private String nombre;

    @ToString.Include
    @Column(nullable = false)
    private Double precio;

    @ManyToMany
    private List<Reserva> reservas;
}
