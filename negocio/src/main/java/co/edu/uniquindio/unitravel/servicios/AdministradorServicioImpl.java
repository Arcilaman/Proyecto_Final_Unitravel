package co.edu.uniquindio.unitravel.servicios;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Vuelo;
import co.edu.uniquindio.unitravel.repositorios.AdministradorHotelRepo;
import co.edu.uniquindio.unitravel.repositorios.AdministradorRepo;
import co.edu.uniquindio.unitravel.repositorios.CiudadRepo;
import co.edu.uniquindio.unitravel.repositorios.VueloRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorServicioImpl implements AdministradorServicio{

    AdministradorHotelRepo adminHotelRepo;
    AdministradorRepo administradorRepo;
    CiudadRepo ciudadRepo;
    EmailServicio emailServicio;
    VueloRepo vueloRepo;

    public AdministradorServicioImpl(AdministradorHotelRepo adminHotelRepo, AdministradorRepo administradorRepo, CiudadRepo ciudadRepo, EmailServicio emailServicio, VueloRepo vueloRepo) {
        this.adminHotelRepo = adminHotelRepo;
        this.administradorRepo = administradorRepo;
        this.ciudadRepo = ciudadRepo;
        this.emailServicio = emailServicio;
        this.vueloRepo = vueloRepo;
    }

    @Override
    public AdministradorHotel registrarAdminHotel(AdministradorHotel adminHotel) throws Exception {
        AdministradorHotel buscado = ObtenerAdminHotel(adminHotel.getCedula());

        if(buscado!= null){
            throw new Exception("El codigo del administrado hotel ya esta registrado");
        }

        AdministradorHotel adminHotelEmail = buscarPorEmail(adminHotel.getCorreo());

        if(adminHotelEmail!= null){
            throw new Exception("El correo del adminsitrador de hotel ya esta registrado");
        }

        return adminHotelRepo.save(adminHotel);
    }

    private AdministradorHotel buscarPorEmail(String correo) {
        return adminHotelRepo.findByCorreo(correo).orElse(null);
    }

    @Override
    public AdministradorHotel actualizarAdminHotel(AdministradorHotel adminHotel) throws Exception {
        AdministradorHotel buscado= ObtenerAdminHotel(adminHotel.getCedula());
        if(buscado==null){
            throw new Exception(" el adminsitrador de hotel no esxiste");
        }
        return adminHotelRepo.save(adminHotel);
    }

    @Override
    public AdministradorHotel ObtenerAdminHotel(String codigo) {
        return adminHotelRepo.findById(codigo).orElse(null);
    }

    @Override
    public void eliminarAdminHotel(String cedula) throws Exception {
        AdministradorHotel adminHotel= ObtenerAdminHotel(cedula);
        if(adminHotel==null){
            throw new Exception(" el administrador de hotel no esxiste");
        }
        adminHotelRepo.delete(adminHotel);

    }

    @Override
    public List<AdministradorHotel> listarAdminHotel() {
        return adminHotelRepo.findAll();
    }

    @Override
    public Vuelo crearVuelo(Vuelo vuelo) throws Exception {
        Vuelo buscado = obtenerVuelo(vuelo.getCodigo());
        if(buscado!= null){
            throw new Exception("El codigo del vuelo ya esta registrado");
        }
        if(vuelo.getCiudadOrigen()==null)
        {
            throw new Exception("El vuelo no tiene ciudad origen");
        }
        if(vuelo.getCiudadDestino()==null)
        {
            throw new Exception("El vuelo no tiene ciudad destino");
        }
        return vueloRepo.save(vuelo);
    }

    @Override
    public Vuelo editarVuelo(Vuelo vuelo) throws Exception {
        Vuelo buscado = obtenerVuelo(vuelo.getCodigo());
        if(buscado== null){
            throw new Exception("El codigo del vuelo no existe");
        }
        if(vuelo.getCiudadOrigen()==null)
        {
            throw new Exception("El vuelo no tiene ciudad origen");
        }
        if(vuelo.getCiudadDestino()==null)
        {
            throw new Exception("El vuelo no tiene ciudad destino");
        }
        return vueloRepo.save(vuelo);
    }

    @Override
    public void eliminarVuelo(int codigoVuelo) throws Exception {
        Vuelo buscado = obtenerVuelo(codigoVuelo);
        if(buscado==null){
            throw new Exception("El vuelo no existe");
        }
        vueloRepo.delete(buscado);
    }

    @Override
    public Vuelo obtenerVuelo(int codigoVuelo) throws Exception {
        return vueloRepo.findById(codigoVuelo).orElse(null) ;
    }



    @Override
    public List<Vuelo> listarVuelos() {
        return vueloRepo.findAll();
    }

    @Override
    public Ciudad crearCiudad(Ciudad ciudad) throws Exception {
        Ciudad buscado = obtenerCiduad(ciudad.getCodigo());
        if(buscado != null)
        {
            throw new Exception("el código de la ciudad ya existe");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad actualizarCiduad(Ciudad ciudad) throws Exception {
        Ciudad buscado= obtenerCiduad(ciudad.getCodigo());
        if(buscado == null)
        {
            throw new Exception("la Ciudad no existe");
        }
        return ciudadRepo.save(ciudad);
    }

    @Override
    public Ciudad obtenerCiduad(int codigoCiudad) throws Exception {
        return ciudadRepo.findById(codigoCiudad).orElse(null);

    }

    @Override
    public void eliminarCiudad(int codigoCiudad) throws Exception {
        Ciudad ciudad = obtenerCiduad(codigoCiudad);
        if(ciudad==null){
            throw new Exception("La ciudad no existe");
        }
        ciudadRepo.delete(ciudad);
    }

    @Override
    public List<Ciudad> ListarCiudades() {return ciudadRepo.findAll(); }
}
