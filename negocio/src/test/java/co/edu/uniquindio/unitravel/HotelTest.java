package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelTest {

    @Autowired
    private HotelRepo hotelRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void buscar(){
        Hotel hotel = hotelRepo.findById(5).orElse(null);
        System.out.println(hotel);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarHotelesCincoEstrellas(){
        List<Hotel> hoteles = hotelRepo.findAllByNumEstrellas(5);
        hoteles.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listar(){
        List<Hotel> lista = hotelRepo.findAll();
        System.out.println(lista);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarHotelesSort(){
        List<Hotel> hoteles = hotelRepo.findAll(Sort.by(Sort.Direction.DESC, "numEstrellas"));
        hoteles.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerNombreCiudad(){
        String nombreCiudad = hotelRepo.obtenerNombreciudad(2);
        System.out.println(nombreCiudad);

        Assertions.assertEquals("Armenia", nombreCiudad);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerListaHoteles(){
        List<Hotel> hoteles = hotelRepo.obtenerHoteles("Armenia");
        hoteles.forEach(System.out::println);

//        Assertions.assertEquals("Armenia", nombreCiudad);
    }






}
