package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Cama;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamaRepo extends JpaRepository<Cama, Integer> {
    /**
     * retorna las habitaciones dado un codigo de cama
     * @param codigoCama
     * @return
     */
    @Query("select  h from Cama c join c.habitaciones h where c.codigo = :codigoCama")
    List<Habitacion> obtenerHabitacion(int codigoCama);

    /**
     * retorna los hoteles a los que pertenece una cama dado un código de cama
     * @param codigoCama
     * @return
     */
    @Query("select h from Cama c join c.habitaciones ha join ha.hotel h where c.codigo= :codigoCama")
    List<Hotel> obtenerHoteles(int codigoCama);
}
