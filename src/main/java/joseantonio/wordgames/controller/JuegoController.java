package joseantonio.wordgames.controller;

import joseantonio.wordgames.model.Juego;
import joseantonio.wordgames.repo.JuegoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class JuegoController {

    private final JuegoRepo juegoRepo;

    //todos los juegos
    @GetMapping("/juegos")
    public ResponseEntity<?> getAllGames(){
        List<Juego> allGames = juegoRepo.findAll();

        if(allGames.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allGames);
    }

    //juegos por id
    @GetMapping("juegos/{id}")
    public ResponseEntity<?> getGame(@PathVariable Long id){
        Juego game = juegoRepo.findById(id).orElse(null);

        if(game == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game);
    }


}
