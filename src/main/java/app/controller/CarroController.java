package app.controller;

import app.entity.Carro;
import app.repository.CarroRepository;
import app.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;
    @Autowired
    private CarroRepository carroRepository;

    @PostMapping("/save")
    public ResponseEntity<String> save (@RequestBody Carro carro){

        try {
            String mensagem = this.carroService.save(carro);
            return new ResponseEntity<String>(mensagem, HttpStatus.OK);
        } catch (Exception e){

            return new ResponseEntity<String>("Deu algo errado ao salvar!", HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/find")
    public ResponseEntity<List<Carro>> getTodosCarros(){
        try {
            List<Carro> carros = carroRepository.findAll();
            if(carros.isEmpty()){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(carros);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Carro> findById(@PathVariable Long id){
        try {
            Carro carro = this.carroService.findById(id);
            return new ResponseEntity<>(carro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById (@PathVariable Long id){
        try {
            this.carroRepository.deleteById(id);
            return new ResponseEntity<String>("Deletado com sucesso!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<String> updateById(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
        try {
            // Supõe-se que o método updateById na service retorna true se a atualização for bem-sucedida
            boolean atualizado = carroService.updateById(id, carroAtualizado);
            if (atualizado) {
                return ResponseEntity.ok("Carro atualizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao atualizar o carro");
        }
    }

}
