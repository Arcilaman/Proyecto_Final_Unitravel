package co.edu.uniquindio.unitravel;

import co.edu.uniquindio.unitravel.entidades.Usuario;
import co.edu.uniquindio.unitravel.servicios.EmailServicio;
import co.edu.uniquindio.unitravel.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class UsuarioServicioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmailServicio emailServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void registarUsuarioTest(){
        Usuario u= new Usuario("0098","Leidy","leidy@gmail.com","osito123");

        List<String> telfs = new ArrayList<>();
        telfs.add("872872");
        telfs.add("893398");

        u.setTelefono(telfs);

        try {
            Usuario guardado= usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(guardado);
        } catch (Exception e) {

             e.printStackTrace();
//
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarUsuarioTest(){
        Usuario usuario = usuarioServicio.ObtenerUsuario("1094");
        usuario.setPassword("817abc");

        try {
            Usuario actualizado= usuarioServicio.actualizarUsuario(usuario);
            Assertions.assertEquals("817abc",actualizado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    @Test
    @Sql("classpath:dataset.sql")
    public void elimarUsuarioTest(){
        try {
            usuarioServicio.eliminarUsuario("1094");
            Usuario eliminado = usuarioServicio.ObtenerUsuario("1094");
            Assertions.assertNull(eliminado);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarUsuarioTest(){
        List<Usuario> lista= usuarioServicio.listarUsuario();
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void loginUsuarioTest(){
        try {
            Usuario usuario= usuarioServicio.validarLogin("pepe@gmail.com","1234");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void enviarCorreoTest(){
        boolean estado = emailServicio.enviarEmail("prueba","Este es un mensaje","santiago.vargasg@uqvirtual.edu.co");
    }

}
