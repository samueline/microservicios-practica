package com.carroservice.controlador;

import com.carroservice.entidades.Carro;
import com.carroservice.servcio.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> listarCarro(){
        List<Carro> carro = carroService.getAll();
        if (carro.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> obtenerCarro(@PathVariable("id") int id){
        Carro carro = carroService.getCarroById(id);
        if (carro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> guardarCarro(@RequestBody Carro carro) {
        Carro nuevoCarro = carroService.save(carro);
        return ResponseEntity.ok(nuevoCarro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosPorIdUser(@PathVariable("usuarioId") int usuarioId){
        List<Carro> carro = carroService.byUsuarioId(usuarioId);
        if (carro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

}
