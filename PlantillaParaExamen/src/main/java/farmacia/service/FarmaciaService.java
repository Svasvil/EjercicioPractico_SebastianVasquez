package farmacia.service; 

import farmacia.domain.Farmacia; 
import java.util.List; 

public interface FarmaciaService {


    public List<Farmacia> getFarmacias(boolean activos); 

    public Farmacia getFarmacia(Farmacia farmacia); 

    public void save(Farmacia farmacia); 

    public void delete(Farmacia farmacia); 
}