package co.edu.uniquindio.unitravel.bean;

import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.servicios.UnitravelServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    @Value("#{param['busqueda']}")
    private String busquedaParam;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private UnitravelServicio unitravelServicio;

    @Getter @Setter
    private List<Hotel> hoteles;

    @PostConstruct
    public void inicializar() throws Exception {
        if (busquedaParam != null && !busquedaParam.isEmpty()) {
            hoteles = unitravelServicio.buscarHotelesNombre(busquedaParam);
        }

    }
    /*
    redireccionamiento a otra pagina
     */

    public String buscar(){

        return "resultados_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }
}
