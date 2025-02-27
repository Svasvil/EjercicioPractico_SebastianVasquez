package farmacia.dao; 

import farmacia.domain.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository; 
import java.util.List; 

public interface FarmaciaDao extends JpaRepository<Farmacia, Long> { 

    List<Farmacia> findByActivoTrue(); 
}