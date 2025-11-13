package org.uv.DAPPWEBPractica07;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Optional;


@RestController
@RequestMapping("/empleados")
public class ControllerEmpleados {

    @Autowired
    private RepositoryEmpleados repositoryEmpleados;

    @GetMapping("/holamundo/{val}")
    public String holamundo(@PathVariable String val) {
        return "Hola Mundo " + val;
    }

    @GetMapping
    public List<Empleados> getEmpleados() {
        return repositoryEmpleados.findAll();
    }

    @GetMapping("/{id}")
    public Empleados findByID(@PathVariable Long id) {
    Optional<Empleados> optionalEmpleados = repositoryEmpleados.findById(id);
    if (!optionalEmpleados.isEmpty()) {
        return optionalEmpleados.get();
    } else {
        return null;
    }
}
    @PostMapping
    public ResponseEntity<Empleados> guardar (@RequestBody Empleados emp){
        Empleados empNew = repositoryEmpleados.save(emp);
        return ResponseEntity.ok(empNew);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empleados> Borrar(@PathVariable Long id){
        Optional<Empleados> optionalEmpleados = repositoryEmpleados.findById(id);
        if (!optionalEmpleados.isEmpty()) {
            repositoryEmpleados.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
public ResponseEntity<Empleados> actualizar(@PathVariable Long id, @RequestBody Empleados empDetalles) {
    Optional<Empleados> optionalEmpleados = repositoryEmpleados.findById(id);

    if (optionalEmpleados.isPresent()) {
        Empleados empExistente = optionalEmpleados.get();

        empExistente.setNombre(empDetalles.getNombre());
        empExistente.setDireccion(empDetalles.getDireccion());
        empExistente.setTelefono(empDetalles.getTelefono());

        Empleados empActualizado = repositoryEmpleados.save(empExistente);
        return ResponseEntity.ok(empActualizado);
    } else {
        return ResponseEntity.notFound().build();
    }
}



}
