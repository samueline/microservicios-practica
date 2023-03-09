package com.usuarioservice.service;

import com.usuarioservice.entidades.Usuario;
import com.usuarioservice.feignclients.CarroFeignClient;
import com.usuarioservice.feignclients.MotoFeignClient;
import com.usuarioservice.models.Carro;
import com.usuarioservice.models.Moto;
import com.usuarioservice.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;


    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8082/carro/usuario/" + usuarioId ,List.class) ;
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/" + usuarioId ,List.class) ;
        return motos;
    }

    public Carro saveCarro(int usuarioId,Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId,Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto = motoFeignClient.save(moto);
        return nuevoMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String,Object> resultado = new HashMap<>();
        Usuario usuario = usuarioRepositorio.findById(usuarioId).orElse(null);
        if (usuario == null){
            resultado.put("Mensaje","El usuario ni existe");
            return resultado;
        }
        resultado.put("Usuario",usuario);
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if (carros.isEmpty()){
            resultado.put("Mensaje","El usuario no tiene carros");
        }else{
            resultado.put("Carros",carros);
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if (motos.isEmpty()){
            resultado.put("Mensaje","El usuario no tiene motos");
        }else{
            resultado.put("Motos",motos);
        }
        return resultado;
    }


    public List<Usuario> getAll(){
        return usuarioRepositorio.findAll();
    }
    public Usuario getUsuarioById(int id){
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUser = usuarioRepositorio.save(usuario);
        return nuevoUser;
    }
}
