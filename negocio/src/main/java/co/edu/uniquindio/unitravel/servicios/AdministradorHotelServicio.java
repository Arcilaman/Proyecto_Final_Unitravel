package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;

import java.util.List;

public interface AdministradorHotelServicio {

    Hotel crearHotel(Hotel hotel) throws Exception;
    void eliminarHotel(int codigo) throws Exception;

    List<Hotel> listarHoteles(String codigoAdmin);

    Hotel modificarHotel(Hotel hotel) throws Exception;

    Habitacion crearHabitacion(Habitacion habitacion) throws  Exception;

    Habitacion actualizarHabitacion(Habitacion habitacion) throws Exception;

    Habitacion obtenerHabitacion(int codigo) throws Exception;

    void eliminarHabitacion(int codigo) throws Exception;

    List<Habitacion> listarHabitaciones() throws Exception ;

    List<Habitacion> listarHabitaciones(int codigoHotel) throws Exception;

    Hotel obtenerHotel(Integer codigoHotel) throws Exception;


    }
