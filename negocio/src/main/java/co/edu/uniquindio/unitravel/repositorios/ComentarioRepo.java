package co.edu.uniquindio.unitravel.repositorios;

import co.edu.uniquindio.unitravel.dto.ComentarioDto;
import co.edu.uniquindio.unitravel.entidades.Comentario;
import co.edu.uniquindio.unitravel.entidades.Hotel;
import co.edu.uniquindio.unitravel.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {

    /**
     * retorna el usuario que realizó un comentario dado el codigo del comentario
     * @param codigoComentario
     * @return
     */
    @Query("select c.usuario from Comentario c where c.codigo = :codigoComentario")
    Usuario obtenerCliente (int codigoComentario);


    /**
     * retorna el hotel al que se le realizó un comentario dado el codigo del comentario
     * @param codigoComentario
     * @return
     */
    @Query("select h from Comentario  c join c.hotel h where c.codigo = :codigoComentario")
    List <Hotel> obtenerHotel (int codigoComentario);

    /**
     * retorna comentarios de un hotel dado el codigo del hotel
     * @param codHotel
     * @return
     */
    @Query("select c from Comentario c join c.hotel h where h.codigo = :codHotel")
    List<Comentario> obtenerComentarios(int codHotel);

    /**
     * retorna los usuarios con sus respectivos comentarios a sus respectivos hoteles
     * @return
     */
    @Query("select new co.edu.uniquindio.unitravel.dto.ComentarioDto(c.usuario, c, c.hotel) from Comentario c")
    List<ComentarioDto> obtenerUsuarioComentarioHotel();



}
