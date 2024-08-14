package app.service;

import app.entity.Carro;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    public String save(Carro carro){
        return "Carro salvo com sucesso!";
    }
}
