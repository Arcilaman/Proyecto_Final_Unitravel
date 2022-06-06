package co.edu.uniquindio.unitravel;


import co.edu.uniquindio.unitravel.dto.ReservaDto;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import co.edu.uniquindio.unitravel.repositorios.ReservaRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReservaTest {

    @Autowired
    private ReservaRepo reservaRepo;

//    @Test
//    @Sql("classpath:dataset.sql")
//    public void listarReservas(){
//        LocalDate fecha = LocalDate.of(2022,05,22);
//        List<Object[]> reservas = reservaRepo.obtenerReservas(1, fecha);
//        reservas.forEach(r -> System.out.println(r[0]+" - "+r[1]+" - "+r[2]) );
//    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarVuelos(){
        List<Vuelo> vuelos = reservaRepo.obtenerReservaVuelos(1);
        vuelos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelUsuario(){
        List<ReservaDto> hotelesUsuarios = reservaRepo.obtenerHotelUsuario(1);
        hotelesUsuarios.forEach(System.out::println);
    }

}
