package co.edu.uniquindio.unitravel.entidades;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends  Persona implements Serializable {

    @ElementCollection
    private List<String> telefonos;

    public Usuario(String cedula, String nombre, @Email String correo, String password) {
        super(cedula, nombre, correo, password);
    }



    @ManyToOne
    private Ciudad ciudad;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany (mappedBy = "usuario")
    private List<Reserva> reservas;


    public void setTelefono(List<String> telfs) {
        this.telefonos = telfs;
    }
}
