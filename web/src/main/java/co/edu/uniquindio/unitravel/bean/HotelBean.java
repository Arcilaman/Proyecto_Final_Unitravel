package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.AdministradorHotel;
import co.edu.uniquindio.unitravel.entidades.Caracteristica;
import co.edu.uniquindio.unitravel.entidades.Ciudad;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.AdministradorHotelServicio;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class HotelBean implements Serializable {


    @Getter @Setter
    private Hotel hotel;


    @Value("${upload.url}")
    private String urlImagenes;

    @Getter @Setter
    private ArrayList<String> imagenes;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Setter @Getter
    private List<Caracteristica> caracteristicas;
    @Autowired
    private AdministradorHotelServicio administradorHotelServicio;

    @Autowired
    private UnitravelServicio unitravelServicio;


    @PostConstruct
    public void inicializar(){
        hotel = new Hotel();
        imagenes = new ArrayList<>();
        ciudades = unitravelServicio.obtenerCiudades();
        caracteristicas = unitravelServicio.listarCaracteristicaHotel();
    }

    public String registrarHotel()
    {
        try {

            if(imagenes.size()>0){

                AdministradorHotel admin = unitravelServicio.obtenerAdminHotel("1");

                hotel.setAdministrador_hotel(admin);
                hotel.setFotos(imagenes);

                administradorHotelServicio.crearHotel(hotel);

                /*FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Hotel creado correctamente");
                FacesContext.getCurrentInstance().addMessage(null, ms);*/
                return "registro_exitoso?faces-redirect=true";
            }else{
                FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Es obligatorio subir " +
                        "las imagenes del hotel");
                FacesContext.getCurrentInstance().addMessage(null, ms);
            }

        }catch (Exception e){
            FacesMessage ms = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, ms);
        }
        return null;
    }

    public void subirImagenes(FileUploadEvent event){

        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen!=null){
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen){

        try {
            File archivo = new File(urlImagenes+"/"+imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }
}
