package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Reserva;
import co.edu.uniquindio.unitravel.entidades.Silla;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepo extends JpaRepository<Reserva, Integer> {


//    @Query("select r.usuario.nombre, r.fechaReserva, r.habitaciones from Reserva r join r.habitaciones h where h.hotel.codigo = :codigoHotel and r.fechaInicio < :fecha")
//    List<Object[]> obtenerReservas(int codigoHotel, LocalDate fecha);

    /**
     * retorna los vuelos
     * @return
     */
    @Query("select v from Reserva r join r.reserva_sillas s join s.silla.vuelo v where r.codigo= :codReserva")
    List<Vuelo> obtenerReservaVuelos(int codReserva);

    @Query("select s.silla from Reserva r join r.reserva_sillas s where r.codigo =:codReserva")
    List<Silla> obtenerSillas(int codReserva);

    @Query("select rs.silla from Reserva r join r.reserva_sillas rs where r.fechaInicio =:fechaInicio and rs.silla.codigo =:codSilla")
    List<Silla> obtenerSillaReservada(LocalDate fechaInicio, String codSilla);

    @Query("select rh.habitacion from Reserva r join r.reserva_habitaciones rh where r.codigo =:codReserva")
    List<Habitacion> obtenerHabitaciones(int codReserva);

    @Query("select rh.habitacion from Reserva  r join r.reserva_habitaciones rh " +
            "where r.fechaInicio between :fechaInicio and :fechaFin or " +
            "r.fechaFin between :fechaInicio and :fechaFin")
    List<Habitacion> obtenerHabitacionesReservadas(LocalDate fechaInicio, LocalDate fechaFin);

    @Query("select rs.silla.vuelo from Reserva r join r.reserva_sillas rs " +
            "where rs.silla.vuelo.ciudadOrigen.codigo=:codCiudadUsuario " +
            "or rs.silla.vuelo.ciudadDestino.codigo=:codCiudadHotel ")
    List<Vuelo> recomendarVuelos(int codCiudadUsuario, int codCiudadHotel );

    @Query("select rs.silla from Reserva r join r.reserva_sillas rs where r.fechaInicio=:fechaIda and " +
            "rs.silla.vuelo.codigo=:codVuelo")
    List<Silla> sillasVueloOcupadas(int codVuelo, LocalDate fechaIda);



//    @Query("select new co.edu.uniquindio.unitravel.dto.ReservaDto(u, h) from Reserva  r join r.reserva_habitaciones rh join rh.habitacion.hotel h join r.usuario u where r.codigo = :codReserva")
//    List<ReservaDto> obtenerHotelUsuario(int codReserva);
}

