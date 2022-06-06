package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@MappedSuperclass
@ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    @ToString.Include
    private String cedula;

    @Column(nullable = false, length = 100)
    @ToString.Include
    private String nombre;

    @ToString.Include
    @Email
    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @ToString.Include
    @Column(nullable = false, length = 50)
    private String password;
}
