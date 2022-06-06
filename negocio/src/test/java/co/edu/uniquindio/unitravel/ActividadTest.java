package co.edu.uniquindio.unitravel;


import co.edu.uniquindio.unitravel.entidades.Actividad;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.ActividadRepo;
import co.edu.uniquindio.unitravel.repositorios.ReservaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ActividadTest {

    @Autowired
    private ActividadRepo actividadRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void listarActividades(){
        List<Actividad> actividades = actividadRepo.obtenerActividades();
        actividades.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuariosActividad(){
        List<Usuario> usuarios = actividadRepo.obtenerUsuarioActividad(2);
        usuarios.forEach(System.out::println);
    }

}
