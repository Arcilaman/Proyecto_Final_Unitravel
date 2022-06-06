package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int codigo;

    @ToString.Include
    @Column(nullable = false)
    private String comentario;

    @ToString.Include
    @PositiveOrZero
    @Max(5)
    @Column(nullable = false)
    private int calificacion;

    @ToString.Include
    @Column(nullable = false)
    private LocalDateTime fechaCalificacion;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Hotel hotel;

    @ManyToOne
    private Usuario usuario;

    public Comentario(String comentario, int calificacion, Hotel hotel, Usuario usuario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fechaCalificacion = LocalDateTime.now();
        this.hotel = hotel;
        this.usuario = usuario;
    }
}
