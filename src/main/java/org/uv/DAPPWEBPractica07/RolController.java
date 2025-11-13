package org.uv.DAPPWEBPractica07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @PostMapping("/")
    public String crearRol(@RequestBody Rol rol) {
        rolRepository.save(rol);
        return "Rol creado correctamente";
    }
}