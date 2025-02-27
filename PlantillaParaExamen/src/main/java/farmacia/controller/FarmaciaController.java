package farmacia.controller;

import farmacia.domain.Farmacia;
import farmacia.service.FarmaciaService;
import farmacia.service.Impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/farmacia")
public class FarmaciaController {

    @Autowired
    private FarmaciaService farmaciaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var farmacias = farmaciaService.getFarmacias(false);
        log.info("Cantidad de farmacias: " + farmacias.size());
        model.addAttribute("farmacias", farmacias);
        model.addAttribute("totalFarmacias", farmacias.size());
        return "farmacia/listado";
    }

    @GetMapping("/eliminar/{idFarmacia}")
    public String eliminar(Farmacia farmacia) {
        farmaciaService.delete(farmacia);
        return "redirect:/farmacia/listado";
    }

    @GetMapping("/modificar/{idFarmacia}")
    public String modificar(Farmacia farmacia, Model model) {
        farmacia = farmaciaService.getFarmacia(farmacia);
        model.addAttribute("farmacia", farmacia);
        return "farmacia/modificar";
    }

    @PostMapping("/modificar")
    public String modificarGuardar(Farmacia farmacia,
            @RequestParam(name = "imagenFile", required = false) MultipartFile imagenFile) {
        if (imagenFile != null && !imagenFile.isEmpty()) {
            farmacia.setRutaImagen(
                    firebaseStorageServiceImpl.cargaImagen(imagenFile, "farmacia", farmacia.getIdFarmacia()));
        } else {
            Farmacia farmaciaExistente = farmaciaService.getFarmacia(farmacia);
            if (farmaciaExistente != null && farmaciaExistente.getRutaImagen() != null) {
                farmacia.setRutaImagen(farmaciaExistente.getRutaImagen());
            }
        }
        farmaciaService.save(farmacia);
        return "redirect:/farmacia/listado";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageServiceImpl;

    @PostMapping("/guardar")
    public String farmaciaGuardar(Farmacia farmacia,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            farmaciaService.save(farmacia);
            farmacia.setRutaImagen(
                    firebaseStorageServiceImpl.cargaImagen(imagenFile, "farmacia", farmacia.getIdFarmacia()));
        }
        farmaciaService.save(farmacia);
        return "redirect:/farmacia/listado";
    }
}
