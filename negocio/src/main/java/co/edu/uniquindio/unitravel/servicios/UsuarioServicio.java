package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;

import java.util.List;


public interface UsuarioServicio {


    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario actualizarUsuario(Usuario usuario) throws  Exception;

    Usuario ObtenerUsuario(String codigo) ;

    void eliminarUsuario(String cedula) throws Exception;

    List<Usuario> listarUsuario()  ;

    Comentario crearComentario(Comentario comentario) throws Exception;

    List<Vuelo> obtenerVuelosRecomendados(Reserva reserva) throws Exception;

    Reserva hacerReserva(Reserva reserva) throws  Exception;

    void eliminarResserva(Integer codigo) throws Exception;

    Reserva modificarReserva(Reserva reserva) throws Exception;

    Reserva obtenerReserva(int codReserva) throws  Exception;

    List<Reserva> listarReservas(String emailUsuario);

    void recuperarPassword(String email) throws Exception;


}
