package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteriscaRepo extends JpaRepository<Caracteristica, Integer> {

    /**
     * retorna los hoteles
     * @return
     */
    @Query("select h from Caracteristica  c  left join c.hoteles h")
    List<Hotel> obtenerHoteles();


    /**
     * retorna las habitaciones
     * @return
     */
    @Query("select h from Caracteristica  c  left join c.habitacion h")
    List<Hotel> obtenerHabitaciones();

    /**
     * Retorna los hoteles dado el codigo del comentario
     * @param codCaracteristica
     * @return
     */
    @Query("select h from Caracteristica c join c.hoteles h where c.codigo= :codCaracteristica")
    List<Hotel> obtenerHoteles(int codCaracteristica);

    /**
     * Retorna las habitaciones dado el codigo del comentario
     * @param codCaracteristica
     * @return
     */
    @Query("select h from Caracteristica c join c.habitacion h where c.codigo= :codCaracteristica")
    List<Hotel> obtenerHabitaciones(int codCaracteristica);

    /**
     * retorna las caracteristicas dado elc odigo del hotel
     * @param codHotel
     * @return
     */
    @Query("select c from Caracteristica c join c.hoteles h where h.codigo = :codHotel" )
    List<Caracteristica> obtenerCaracteristicasHotel(int codHotel);

    /**
     * retorna las caracteristicas dado un código de habitación
     * @param numHabitacion
     * @return
     */
    @Query("select c from Caracteristica c join c.habitacion h where h.numero = :numHabitacion" )
    List<Caracteristica> obtenerCaracteristicasHabitacion(int numHabitacion);

    List<Caracteristica> findAllByTipo(String tipo);
}
