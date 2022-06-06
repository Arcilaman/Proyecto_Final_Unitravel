package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Caracteristica implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;

    @ToString.Include
    @Column(length = 30, nullable = false)
    private String nombre;

    @Column(length = 10, nullable = false)
    private String tipo;

    @ManyToMany
    private List<Hotel> hoteles;

    @ManyToMany
    private List<Habitacion> habitaciones;
}
