package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Hotel implements Serializable {

    public Hotel(int codigo, String nombre, String direccion, String telefono, int numEstrellas, AdministradorHotel administrador_hotel, Ciudad ciudad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.numEstrellas = numEstrellas;
        this.administrador_hotel = administrador_hotel;
        this.ciudad = ciudad;
    }

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
    private String direccion;

    @ToString.Include
    private String telefono;

    @ToString.Include
    @PositiveOrZero
    @Max(5)
    private int numEstrellas;

    @ManyToOne
    private AdministradorHotel administrador_hotel;

    @OneToMany(mappedBy = "hotel")
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudad;

    @ManyToMany(mappedBy = "hoteles")
    private List <Caracteristica> caracteristicas;

    @OneToMany(mappedBy = "hotel")
    private List <Habitacion> habitaciones;

    @ElementCollection
    private List <String> fotos;
}
