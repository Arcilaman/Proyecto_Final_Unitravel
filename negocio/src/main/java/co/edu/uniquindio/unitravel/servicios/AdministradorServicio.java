package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;

public interface AdministradorServicio {

    //Gestionar administradores de hotelesq
    AdministradorHotel registrarAdminHotel(AdministradorHotel adminHotel) throws Exception;
    AdministradorHotel actualizarAdminHotel(AdministradorHotel adminHotel) throws  Exception;
    AdministradorHotel ObtenerAdminHotel(String codigo) ;
    void eliminarAdminHotel(String cedula) throws Exception;
    List<AdministradorHotel> listarAdminHotel()  ;

    //gestionar vuelos
    Vuelo crearVuelo(Vuelo vuelo) throws Exception;//para gestionar vuelo necesitamos las sillas
    Vuelo editarVuelo(Vuelo vuelo) throws Exception;
    void eliminarVuelo(int codigoVuelo) throws Exception;
    Vuelo obtenerVuelo(int codigoVuelo) throws Exception;
    List<Vuelo> listarVuelos();

    //gestionar Destinos
    Ciudad crearCiudad(Ciudad ciudad) throws Exception;
    Ciudad actualizarCiduad(Ciudad ciudad) throws Exception;
    Ciudad obtenerCiduad(int codigoCiudad) throws Exception;
    void eliminarCiudad(int codigoCiudad) throws Exception;
    List<Ciudad> ListarCiudades();

}
