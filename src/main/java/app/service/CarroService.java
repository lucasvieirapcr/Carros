package app.service;

import app.entity.Carro;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public String save(Carro carro){

        this.carroRepository.save(carro);

        return "Carro salvo com sucesso!";
    }

    public Carro findById(Long id){
        Optional<Carro> carro = this.carroRepository.findById(id);
        return carro.get();
    }

    public String delete(Long id){
        carroRepository.deleteById(id);
        return "Carro deletado com sucesso";
    }

    public List<Carro> findAll(){
        return carroRepository.findAll();
    }

    public boolean updateById(Long id, Carro carroAtualizado) {
        // Busca o carro existente no banco de dados
        Optional<Carro> carroOptional = carroRepository.findById(id);

        if (carroOptional.isPresent()) {
            Carro carroExistente = carroOptional.get();

            carroExistente.setModelo(carroAtualizado.getModelo());
            carroExistente.setMarca(carroAtualizado.getMarca());
            carroExistente.setAnoLancamento(carroAtualizado.getAnoLancamento());

            carroRepository.save(carroExistente);
            return true;
        } else {
            return false;
        }
    }
}
