package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.Habitacion;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.HabitacionRepo;
import co.edu.uniquindio.unitravel.repositorios.HotelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorHotelServicioImpl implements AdministradorHotelServicio{

    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private EmailServicio emailServicio;
    @Autowired
    private AdministradorHotelRepo administradorHotelRepo;

    @Autowired
    private HabitacionRepo habitacionRepo;



    @Override
    public Hotel crearHotel(Hotel hotel) throws Exception {
        Hotel buscado = obtenerHotel(hotel.getCodigo());

        if(buscado!= null){
            throw new Exception("El hotel del usuario ya esta registrado");
        }
        if(hotel.getNumEstrellas()>5 && hotel.getNumEstrellas()<0)
        {
            throw new Exception("el número de estrellas es inválido");
        }
        else {
            System.out.println("Hotelrepo save");
            return hotelRepo.save(hotel);
        }
    }

    @Override
    public void eliminarHotel(int codigo) throws Exception {
        Hotel buscado = obtenerHotel(codigo);
        if(buscado==null)
        {
            throw new Exception("El hotel no existe ");
        }
        hotelRepo.delete(buscado);
    }

    @Override
    public List<Hotel> listarHoteles(String codigoAdmin) {

        return hotelRepo.findAll();
    }

    @Override
    public Hotel modificarHotel(Hotel hotel) throws Exception {
        Hotel buscado = obtenerHotel(hotel.getCodigo());
        if(buscado==null)
        {
            throw new Exception("El hotel no existe");
        }
        if(hotel.getNumEstrellas()>5 && hotel.getNumEstrellas()<0)
        {
            throw new Exception("el número de estrellas es inválido");
        }
        return hotelRepo.save(hotel);

    }

    @Override
    public Habitacion crearHabitacion(Habitacion habitacion) throws Exception {
        Habitacion buscado = obtenerHabitacion(habitacion.getNumero());
        if(buscado != null){
            throw new Exception("La habitación ya existe");
        }
        return habitacionRepo.save(habitacion);

    }

    @Override
    public Habitacion actualizarHabitacion(Habitacion habitacion) throws Exception {
        Habitacion buscado = obtenerHabitacion(habitacion.getNumero());
        if(buscado == null){
            throw new Exception("La habitación no existe");
        }

        return habitacionRepo.save(habitacion);

    }

    @Override
    public Habitacion obtenerHabitacion(int codigo) throws Exception {
        return habitacionRepo.findById(codigo).orElse(null);
    }

    @Override
    public void eliminarHabitacion(int codigo) throws Exception {
        Habitacion buscado = obtenerHabitacion(codigo);
        if(buscado == null){
            throw new Exception("La habitación no existe");
        }
        habitacionRepo.delete(buscado);
    }

    @Override
    public List<Habitacion> listarHabitaciones() throws Exception {
        return habitacionRepo.findAll();
    }

    @Override
    public List<Habitacion> listarHabitaciones(int codigoHotel) throws Exception {
        return habitacionRepo.obtenerHabitacionHotel(codigoHotel);
    }

    @Override
    public Hotel obtenerHotel(Integer codigoHotel) throws Exception {
        return hotelRepo.findById(codigoHotel).orElse(null);
    }


}
