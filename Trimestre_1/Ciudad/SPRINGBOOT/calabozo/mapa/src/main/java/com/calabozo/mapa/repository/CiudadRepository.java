package com.calabozo.mapa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.calabozo.mapa.model.Ciudad;
import java.util.List;


public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

    public List<Ciudad> findByPais(String pais);

    public List<Ciudad> findByPaisIn(List<String> listaPaises);

    // Devolver las 5 ciudades con mayor extension
    public List<Ciudad> findTop5ByExtension(int extension);

    // Mostrar la primera ciudad cuyo nombre sea el introducido con mayor extension
    public Ciudad findFirstByNombreOrderByExtensionDesc(String nomCiudad);

    // Contar numero de ciudades que tengan la extension mayor que
    public int countByExtensionGreaterThan(int extension);

    // Sacar la lista de ciudades con pais como el patron de nombre igual al
    // recibido y con extension mayor o igual a la recibida
    public List<Ciudad> findByPaisLikeAndNombreOrExtensionGreaterThanEqual(String patron, String nombre, int extension);

    /**
     * Obtener estadísticas de ciudades por país usando SQL nativo
     */
    @Query(value = "SELECT pais, COUNT(*) as total, AVG(num_habitantes) as promedio_habitantes, " +
            "SUM(extension) as extension_total FROM ciudad GROUP BY pais", nativeQuery = true)
    List<Object[]> obtenerEstadisticasPorPais();

    /**
     * Buscar ciudades con al menos X calabozos (usando JOIN)
     */
    @Query(value = "SELECT c.* FROM ciudad c " +
            "LEFT JOIN calabozo cb ON c.id = cb.ciudad_id " +
            "GROUP BY c.id HAVING COUNT(cb.id) >= :minCalabozos", nativeQuery = true)
    List<Ciudad> ciudadesConMinCalabozos(@Param("minCalabozos") int minCalabozos);


        


}
