package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Reserva implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codigo;
    @ToString.Include
    @Column(nullable = false)
    private LocalDate fechaReserva;
    @ToString.Include
    @Column(nullable = false)
    private LocalDate fechaInicio;
    @ToString.Include
    @Column(nullable = false)
    private LocalDate fechaFin;
    @ToString.Include
    private Double precioTotal;

    @ToString.Include
    @Column(length = 10, nullable = false)
    private String estado;
    @ToString.Include
    @Column(nullable = false)
    private int cantidadPersona;

    public Reserva(int codigo, LocalDate fechaReserva, LocalDate fechaInicio, LocalDate fechaFin, int cantidadPersona) {
        this.codigo = codigo;
        this.fechaReserva = fechaReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersona = cantidadPersona;
    }

    @ManyToOne
    private Usuario usuario;



    @ManyToMany(mappedBy = "reservas")
    private List<Vehiculo> vehiculos;

    @ManyToMany(mappedBy = "reservas")
    private List<Actividad> actividades;

    @OneToMany(mappedBy = "reserva")
    private List<Reserva_Silla> reserva_sillas;

    @OneToMany(mappedBy = "reserva")
    private List<Reserva_Habitacion> reserva_habitaciones;


    public Double getPrecioTotalCalculado() {
        this.precioTotal=0.0;
        for ( Reserva_Habitacion reserva_habitacion: reserva_habitaciones ) {
            precioTotal+=reserva_habitacion.getHabitacion().getPrecio() ;
        }
        for ( Reserva_Silla reserva_silla: reserva_sillas ) {
            precioTotal+=reserva_silla.getSilla().getPrecio();
        }
        return precioTotal;
    }
}
