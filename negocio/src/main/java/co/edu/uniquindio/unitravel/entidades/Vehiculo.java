package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Vehiculo implements Serializable {


    @Id
    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(length = 6, nullable = false)
    private String placa;

    @ToString.Include
    @Column(length = 30, nullable = false)
    private String tipo;

    @ToString.Include
    @Column(nullable = false)
    private Double precio;

    @ManyToMany
    private List<Reserva> reservas;
}
