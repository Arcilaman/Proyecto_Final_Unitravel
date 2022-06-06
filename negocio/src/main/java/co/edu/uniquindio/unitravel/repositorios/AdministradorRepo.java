package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, String> {

    Optional<Administrador>findByCorreoAndPassword(String correo, String password);


    @Query("select a from Administrador a where a.correo= :correo and a.password= :password ")
    Administrador comprobarAutenticacion(String correo, String password);


    Optional<Administrador> findByCorreo(String email);
}
