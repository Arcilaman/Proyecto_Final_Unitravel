package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepo extends JpaRepository<Habitacion, Integer> {


    /**
     * obtener reservas dado el código de habitación
     * @param numHabitacion
     * @return
     */
    @Query("select r from Habitacion h join h.reserva_habitaciones rh join rh.reserva r where h.numero= :numHabitacion")
    List<Reserva> obtenerReservas(int numHabitacion);


    /**
     * obtener habitación dado el código de reserva
     * @param codReserva
     * @return
     */
    @Query("select h from Habitacion h join h.reserva_habitaciones rh join rh.reserva r where r.codigo= :codReserva")
    List<Habitacion> obtenerHabitaciones(int codReserva);

    /**
     * obtener camas dado el código de habitación
     * @param numHabitacion
     * @return
     */
    @Query("select c from Habitacion h join  h.camas c where h.numero= :numHabitacion")
    List<Cama> obtenerCamas(int numHabitacion);



    /**
     * obtener camas
     * @param
     * @return
     */
    @Query("select c from Habitacion h join  h.camas c")
    List<Cama> obtenerCamas();
    

    /**
     * obtener hotel dado el código de habitación
     * @param codHabitacion
     * @return
     */
    @Query("select ho from Habitacion h join  h.hotel ho where h.numero= :codHabitacion")
    List<Hotel> obtenerHoteles(int codHabitacion);


    /**
     * obtener hoteles
     * @param
     * @return
     */
    @Query("select ho from Habitacion h join  h.hotel ho")
    List<Hotel> obtenerHoteles();




    /**
     * obtener habitaciones dado el código del hotel
     * @param codHotel
     * @return
     */
    @Query("select h from Habitacion h where h.hotel.codigo=:codHotel")
    List<Habitacion> obtenerHabitacionHotel(int codHotel);
}
