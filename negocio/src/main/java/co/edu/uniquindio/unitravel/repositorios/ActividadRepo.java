package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Actividad;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepo extends JpaRepository<Actividad, Integer> {


    @Query("select  a from Actividad a")
    List<Actividad> obtenerActividades();

    /**
     * consulta para obtener los usuarios que reservaron una actividad dada
     * @param codigoActividad
     * @return
     */
    @Query("select r.usuario from Actividad  a join a.reservas r where a.codigo = :codigoActividad")
    List<Usuario> obtenerUsuarioActividad(int codigoActividad);

    /**
     * consulta para obtener las reservas dado un codigo de una activdad
     * @param codigoActividad
     * @return
     */
    @Query("select r from Actividad a join a.reservas r where a.codigo = :codigoActividad")
    List<Reserva> obtenerReservas(int codigoActividad);







}
