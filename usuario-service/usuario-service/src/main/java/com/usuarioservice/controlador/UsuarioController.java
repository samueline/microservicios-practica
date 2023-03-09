package com.usuarioservice.controlador;

import com.usuarioservice.entidades.Usuario;
import com.usuarioservice.models.Carro;
import com.usuarioservice.models.Moto;
import com.usuarioservice.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){
        List<Usuario> usuarios = usuarioService.getAll();
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        Usuario nuevoUser =usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUser);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> getCarros(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Carro> carros = usuarioService.getCarros(id);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> getMotos(@PathVariable("usuarioId") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if (usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(id);
        return ResponseEntity.ok(motos);
    }
    @PostMapping("/carro/{usuarioId}")
    public ResponseEntity<Carro> guardarCarro(@PathVariable("usuarioId") int usuarioId,@RequestBody Carro carro){
        Carro nuevoCarro = usuarioService.saveCarro(usuarioId, carro);
        return ResponseEntity.ok(nuevoCarro);
    }
    @PostMapping("/moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") int usuarioId,@RequestBody Moto moto){
        Moto nuevoMoto = usuarioService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(nuevoMoto);
    }

    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String,Object>> listAllVehicles(@PathVariable("usuarioId") int usuarioId){
        Map<String,Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }


}
