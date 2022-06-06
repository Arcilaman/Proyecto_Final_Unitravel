package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {
    @Query("select  h from Hotel h where h.numEstrellas = :estrellas")
    List<Hotel> obtenerHoteles(int estrellas);

    List<Hotel> findAllByNumEstrellas(int estrellas);

//    List<Hotel> findAllByTelefono(String telefono);


//    @Query("select  h from Hotel h where h.numEstrellas >= :estrellas")
//    List<Hotel> obtenerHoteles(int estrellas);

    @Query("select  h.ciudad.nombre from Hotel h where h.codigo = :codigoH")
    String obtenerNombreciudad (int codigoH);

    @Query("select  h from Hotel h where h.ciudad.nombre = :nombreCiudad")
    List<Hotel> obtenerHoteles(String nombreCiudad);

    @Query("select h from Hotel h where lower(h.nombre) like concat('%', lower(:nombreHotel), '%')")
    List<Hotel> obtenerHotelesNombre(String nombreHotel);

}
