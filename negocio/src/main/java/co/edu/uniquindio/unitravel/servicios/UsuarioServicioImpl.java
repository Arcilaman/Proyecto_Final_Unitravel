package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuarioRepo usuarioRepo;
    private ComentarioRepo comentarioRepo;
    private ReservaRepo reservaRepo;
    private HotelRepo hotelRepo;
    private EmailServicio emailServicio;

    private CiudadRepo ciudadRepo;


    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, ComentarioRepo comentarioRepo,
                               ReservaRepo reservaRepo, HotelRepo hotelRepo, EmailServicio emailServicio,
                                    CiudadRepo ciudadRepo){

        this.usuarioRepo= usuarioRepo;
        this.comentarioRepo=comentarioRepo;
        this.reservaRepo = reservaRepo;
        this.hotelRepo = hotelRepo;
        this.ciudadRepo = ciudadRepo;
        this.emailServicio = emailServicio;
    }


    @Override
    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Usuario buscado = ObtenerUsuario(usuario.getCedula());

       // if(){
         //   throw new Exception("El codigo del usuario ya esta registrado");
        //}

        Usuario usuarioEmail = buscarPorEmail(usuario.getCorreo());

        if(usuarioEmail!= null || buscado!= null){
            throw new Exception("El correo del usuario ya esta registrado");
        }
        else {
            return usuarioRepo.save(usuario);
        }
    }


    public  Usuario buscarPorEmail(String email)
    {
        return usuarioRepo.findAllByCorreo(email).orElse(null);

    }
    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws Exception{
        Usuario buscado= ObtenerUsuario(usuario.getCedula());
        if(usuario==null){
            throw new Exception(" el usuario no esxiste");
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario ObtenerUsuario(String cedula) {

        return usuarioRepo.findById(cedula).orElse(null);
    }




    @Override
    public void eliminarUsuario(String cedula) throws  Exception{
        Usuario usuario= ObtenerUsuario(cedula);
        if(usuario==null){
            throw new Exception(" el usuario no esxiste");
        }
        usuarioRepo.delete(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepo.findAll();
    }



    @Override
    public Comentario crearComentario(Comentario comentario) throws Exception {

        if(comentarioRepo.obtenerCliente(comentario.getCodigo())== null)
        {
            throw new Exception("El usuario no existe");
        }

        if(comentarioRepo.obtenerHotel(comentario.getCodigo())==null)
        {
            throw  new Exception("El hotel no existe");
        }
        return comentarioRepo.save(comentario);


    }

    @Override
    public List<Vuelo> obtenerVuelosRecomendados(Reserva reserva) throws Exception {
        Habitacion habitacion = reserva.getReserva_habitaciones().get(0).getHabitacion();

        List<Vuelo> vuelosRecomendados = reservaRepo.recomendarVuelos(reserva.getUsuario().getCiudad().getCodigo(), habitacion.getHotel().getCiudad().getCodigo());
        vuelosRecomendados.forEach(System.out::println);
        return vuelosRecomendados;
    }

    @Override
    public Reserva hacerReserva(Reserva reserva) throws Exception {

        if (obtenerReserva(reserva.getCodigo()) != null) {
            System.out.println(reservaRepo.findById(reserva.getCodigo()));
            throw new Exception("La reserva ya existe");
        }

        if (reserva.getFechaReserva().isAfter(reserva.getFechaInicio())) {
            throw new Exception("La fecha de reserva no puede ser después de la fecha inicio");
        }
        if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
            throw new Exception("La fecha de inicio no puede ser después de la fecha fin");
        }

        List<Habitacion> listaHabitacionesReservadas = reservaRepo.
                obtenerHabitacionesReservadas(reserva.getFechaInicio(),reserva.getFechaFin());
//        listaHabitacionesReservadas.forEach(System.out::println);

        //
        if(!listaHabitacionesReservadas.isEmpty() && !reserva.getReserva_habitaciones().isEmpty()){
            for (Reserva_Habitacion reserva_habitacion: reserva.getReserva_habitaciones()) {
                Habitacion habitacionReserva = reserva_habitacion.getHabitacion();
                for ( Habitacion habitacionReservada: listaHabitacionesReservadas ) {
                    if(habitacionReserva.equals(habitacionReservada))
                    {
                        throw new Exception("La habitación ya está reservada en esa fecha");
                    }
                }
            }
        }

        if (reserva.getReserva_sillas().isEmpty())
        {
            obtenerVuelosRecomendados(reserva).forEach(System.out::println);
        }

        System.out.println(reserva.getPrecioTotalCalculado());

        return reservaRepo.save(reserva);
    }

    @Override
    public void eliminarResserva(Integer codigo) throws Exception {
        Optional<Reserva> reserva= reservaRepo.findById(codigo);
        if(reserva.isEmpty()){
            throw new Exception("la reserva no existe");
        }
        reservaRepo.delete(reserva.get());

    }

    @Override
    public Reserva modificarReserva(Reserva reserva) throws Exception {
        if (obtenerReserva(reserva.getCodigo()) == null) {
            throw new Exception("La reserva no existe");
        }

        if (reserva.getFechaReserva().isAfter(reserva.getFechaInicio())) {
            throw new Exception("La fecha de reserva no puede ser después de la fecha inicio");
        }
        if (reserva.getFechaInicio().isAfter(reserva.getFechaFin())) {
            throw new Exception("La fecha de inicio no puede ser después de la fecha fin");
        }

        List<Habitacion> listaHabitacionesReservadas = reservaRepo.
                obtenerHabitacionesReservadas(reserva.getFechaInicio(),reserva.getFechaFin());
//        listaHabitacionesReservadas.forEach(System.out::println);

        //
        if(!listaHabitacionesReservadas.isEmpty() && !reserva.getReserva_habitaciones().isEmpty()){
            for (Reserva_Habitacion reserva_habitacion: reserva.getReserva_habitaciones()) {
                Habitacion habitacionReserva = reserva_habitacion.getHabitacion();
                for ( Habitacion habitacionReservada: listaHabitacionesReservadas ) {
                    if(habitacionReserva.equals(habitacionReservada))
                    {
                        throw new Exception("La habitación ya está reservada en esa fecha");
                    }
                }
            }
        }



        return reservaRepo.save(reserva);
    }

    @Override
    public Reserva obtenerReserva(int codReserva) throws Exception {
        return reservaRepo.findById(codReserva).orElse(null);
    }


    @Override
    public List<Reserva> listarReservas(String emailUsuario) {
        return usuarioRepo.obtenerListaReservas(emailUsuario);
    }



    @Override
    public void recuperarPassword(String email) throws Exception {

        Optional<Usuario> usuario= usuarioRepo.findByCorreo(email);
        if(usuario.isEmpty()){
            throw new Exception("el correo no pertenece a ningun usuario ");
        }
        String password = usuario.get().getPassword();
        emailServicio.enviarEmail("Recuperacion de la contraseña ", " Hola, hemos recibido " +
                "su solicitud para 'recuperar contraseña' atendiendo su solicitud, realizamos la entrega " +
                "de su contraseña: '"+password,email +"'");

   }
}
