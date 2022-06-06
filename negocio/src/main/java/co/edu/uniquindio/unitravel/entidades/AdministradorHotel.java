package co.edu.uniquindio.unitravel.entidades;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, onlyExplicitlyIncluded = true)
public class AdministradorHotel extends  Persona implements Serializable {

    public AdministradorHotel(String cedula, String nombre, @Email String correo, String password) {
        super(cedula, nombre, correo, password);
    }


    @OneToMany (mappedBy = "administrador_hotel")
    private List <Hotel> hoteles;
}
