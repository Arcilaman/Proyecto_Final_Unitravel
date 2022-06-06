package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Setter
@ToString(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Reserva_Silla {
    @Id
    @ToString.Include
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codigo;

    @ToString.Include
    private Double precio;

    @ManyToOne
    private Reserva reserva;

    @ManyToOne
    private Silla silla;
}
