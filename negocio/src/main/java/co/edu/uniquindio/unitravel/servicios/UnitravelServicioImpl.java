package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.*;
import co.edu.uniquindio.unitravel.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitravelServicioImpl implements UnitravelServicio{
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private HotelRepo hotelRepo;

    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private CaracteriscaRepo caracteriscaRepo;



    @Override
    public Usuario validarLogin(String correo, String password) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findByCorreoAndPassword(correo,password);

        if (usuario.isEmpty()){
            throw new Exception("los datos de autenticacion son incorrectos");
        }

        return usuario.get();
    }

    @Override
    public void recuperarPassword(String email) throws Exception {
        Optional<Usuario> usuario= usuarioRepo.findByCorreo(email);
        if(usuario.isEmpty()){
            throw new Exception("el correo no pertenece a ningun usuario ");
        }
        String password = usuario.get().getPassword();
        emailServicio.enviarEmail("recuperacion de la contraseña ", " hola su contraseña  es"+password,email );

    }



    @Override
    public Ciudad obtenerCiudad(int codCiudad) throws Exception {
        return ciudadRepo.findById(codCiudad).orElse(null);
    }

    @Override
    public List<Ciudad> obtenerCiudades() {
        return ciudadRepo.findAll();
    }

    @Override
    public AdministradorHotel obtenerAdminHotel(String cedula) throws Exception {
        return administradorHotelRepo.findById(cedula).orElse(null);
    }

    @Override
    public List<Hotel> buscarHotelesNombre(String nombre) throws Exception {
        return hotelRepo.obtenerHotelesNombre(nombre);
    }

    @Override
    public Caracteristica obtenerCaracteristica(int id) throws Exception {
        return caracteriscaRepo.findById(id).orElse(null);
    }

    @Override
    public List<Caracteristica> listarCaracteristicaHotel() {
        return caracteriscaRepo.findAllByTipo("1");
    }

    @Override
    public List<Caracteristica> listarCaracteristicaHabitacion() {
        return caracteriscaRepo.findAllByTipo("2");
    }

}
