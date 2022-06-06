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
public class Silla implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ToString.Include
    @Column(nullable = false)
    private String posición;

    @ToString.Include
    @Column(nullable = false)
    private Double precio;

    @OneToMany(mappedBy = "silla")
    private List<Reserva_Silla> reserva_sillas;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Vuelo vuelo;

}
