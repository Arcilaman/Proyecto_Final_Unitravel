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
public class Habitacion implements Serializable {


    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    @ToString.Include
    @Column(nullable = false)
    private Double precio;

    @ToString.Include
    @Column(nullable = false)
    private Integer capacidad;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "habitacion")
    private List<Reserva_Habitacion> reserva_habitaciones;

    @ManyToMany (mappedBy = "habitaciones")
    private List<Caracteristica> caracteristicas;

    @ElementCollection
    private List<String> fotos;

    @ManyToMany(mappedBy = "habitaciones")
    private List<Cama> camas;

}
