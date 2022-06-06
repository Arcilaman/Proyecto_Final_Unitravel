package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CiudadRepo  extends JpaRepository<Ciudad, Integer> {
    /**
     * retorna los hoteles dado el nombre de una ciudad
     * @param nombreCiudad
     * @return
     */
    @Query("select c.hoteles from Ciudad c where c.nombre = :nombreCiudad")
    List<Hotel> obtenerHoteles(String nombreCiudad);

    /**
     * retorna los hoteles dado el nombre de una ciudad
     * @param nombreCiudad
     * @return
     */
    @Query("select h from Ciudad c, IN (c.hoteles) h where c.nombre= :nombreCiudad")
    List<Hotel> obtenerHotelesIn(String nombreCiudad);

    /**
     * retorna los vuelos que salen de una ciudad dado el nombre dicha ciudad
     * @param nombreCiudad
     * @return
     */
    @Query("select v from Ciudad c join c.vuelosOrigen v where c.nombre = :nombreCiudad")
    List<Vuelo> obtenerVuelosOrigen(String nombreCiudad);

    /**
     * retorna los vuelos de las ciudades destino dado el nombre de dicha ciudad
     * @param nombreCiudad
     * @return
     */
    @Query("select v from Ciudad c join c.vuelosDestino v where c.nombre = :nombreCiudad")
    List<Vuelo> obtenerVuelosDestino(String nombreCiudad);


}
