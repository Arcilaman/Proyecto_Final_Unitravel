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
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class Vuelo implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;

    @ToString.Include
    @Column(length = 10, nullable = false)
    private String estado;
    @ToString.Include
    @Column(length = 30, nullable = false)
    private String aerolinea;

    @OneToMany(mappedBy = "vuelo")
    private List<Silla> sillas;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Ciudad ciudadDestino;

}
