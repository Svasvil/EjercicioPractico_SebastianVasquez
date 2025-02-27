package farmacia.service.Impl; 

import farmacia.dao.FarmaciaDao; 
import farmacia.domain.Farmacia;
import farmacia.service.FarmaciaService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.List; 
import java.util.Optional; 

@Service 
public class FarmaciaServiceImpl implements FarmaciaService { 

    @Autowired 
    private FarmaciaDao farmaciaDao; 

    @Override 
    @Transactional(readOnly = true) 
    public List<Farmacia> getFarmacias(boolean activos) { 
        List<Farmacia> lista = farmaciaDao.findAll(); 
        if (activos) { 
            lista.removeIf(e -> !e.isActivo()); 
        }
        return lista; 
    }

    @Override 
    @Transactional(readOnly = true) 
    public Farmacia getFarmacia(Farmacia farmacia) { 
        Optional<Farmacia> optionalFarmacia = farmaciaDao.findById(farmacia.getIdFarmacia()); 
        return optionalFarmacia.orElse(null); 
    }

    @Transactional 
    public void delete(Farmacia farmacia) {

        farmaciaDao.delete(farmacia);
    }

    @Transactional 
    public void save(Farmacia farmacia) { 
    
        farmaciaDao.save(farmacia); 
    }
}