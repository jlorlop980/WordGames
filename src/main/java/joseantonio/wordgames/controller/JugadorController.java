package joseantonio.wordgames.controller;

import joseantonio.wordgames.DTO.NewJugadorDTO;
import joseantonio.wordgames.model.Equipo;
import joseantonio.wordgames.model.Jugador;
import joseantonio.wordgames.repo.EquipoRepo;
import joseantonio.wordgames.repo.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorRepo jugadoRepo;
    private final EquipoRepo equipoRepo;

    @GetMapping("/jugadores")
    public ResponseEntity<?> getAllPlayers(){
        List<Jugador> allPlayers = jugadoRepo.findAll();

        if(allPlayers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allPlayers);
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Long id){
        Jugador player = jugadoRepo.findById(id).orElse(null);

        return (player == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(player);
    }


    @PostMapping("/jugadores")
    public ResponseEntity<?> addPlayer(@RequestBody NewJugadorDTO newPlayer){

        Jugador nuevoJugador = new Jugador();
        Equipo defaultTeam = equipoRepo.findById(8L).orElse(null);

        if(newPlayer.getNombre() != null
                && (newPlayer.getAdmin() == 1 || newPlayer.getAdmin() == 0)
                && newPlayer.getClave() != null){

            nuevoJugador.setNombre(newPlayer.getNombre());
            nuevoJugador.setEquipo(defaultTeam);
            nuevoJugador.setClave(newPlayer.getClave());
            nuevoJugador.setAvatar(newPlayer.getAvatar());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(jugadoRepo.save(nuevoJugador));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        Jugador deletedPlayer = jugadoRepo.findById(id).orElse(null);

        if(deletedPlayer == null){
            return ResponseEntity.notFound().build();
        }

        jugadoRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deletedPlayer);
    }

    @PutMapping("jugadores/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody NewJugadorDTO playerNewData, @PathVariable Long id){
        Jugador modifiedPlayer = jugadoRepo.findById(id).orElse(null);
        if(playerNewData.getEquipo_id() != null){
            Equipo team = equipoRepo.findById(playerNewData.getEquipo_id()).orElse(null);
            if(team != null){
                modifiedPlayer.setEquipo(team);
            }
        }

        if(modifiedPlayer == null){
            return ResponseEntity.notFound().build();
        }


        if (playerNewData.getAdmin() == 0 || playerNewData.getAdmin() == 1)
            modifiedPlayer.setAdmin(playerNewData.getAdmin());

        if (playerNewData.getNombre() != null)
            modifiedPlayer.setNombre(playerNewData.getNombre());

        if (playerNewData.getClave() != null)
            modifiedPlayer.setClave(playerNewData.getClave());

        if (playerNewData.getAvatar() != null)
            modifiedPlayer.setAvatar(playerNewData.getAvatar());

        if (playerNewData.getPuntos() != null)
            modifiedPlayer.setPuntos((playerNewData.getPuntos().intValue()));

        jugadoRepo.save(modifiedPlayer);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(modifiedPlayer);
    }
}
