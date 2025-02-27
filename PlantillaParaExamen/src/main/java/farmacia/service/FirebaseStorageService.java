
package farmacia.service; 

import org.springframework.stereotype.Service; 
import org.springframework.web.multipart.MultipartFile; 

@Service 
public interface FirebaseStorageService { 

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);
   
    final String BucketName = "tienda-7fe4d.firebasestorage.app"; 

   
    final String rutaSuperiorStorage = "techshop";

    final String rutaJsonFile = "firebase"; 


    final String archivoJsonFile = "tienda-7fe4d-firebase-adminsdk-fbsvc-fb6a195cde" + ".json"; 
}