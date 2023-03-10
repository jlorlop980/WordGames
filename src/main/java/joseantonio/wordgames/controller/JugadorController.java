package joseantonio.wordgames.controller;

import joseantonio.wordgames.DTO.ClasificacionDTO;
import joseantonio.wordgames.DTO.NewJugadorDTO;
import joseantonio.wordgames.model.Equipo;
import joseantonio.wordgames.model.Jugador;
import joseantonio.wordgames.repo.EquipoRepo;
import joseantonio.wordgames.repo.JugadorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JugadorController {

    private final JugadorRepo jugadorRepo;
    private final EquipoRepo equipoRepo;

    @GetMapping("/jugadores")
    public ResponseEntity<?> getAllPlayers(){
        List<Jugador> allPlayers = jugadorRepo.findAll();

        if(allPlayers.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allPlayers);
    }

    @GetMapping("/jugadores/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable Long id){
        Jugador player = jugadorRepo.findById(id).orElse(null);

        return (player == null) ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(player);
    }

    @GetMapping("/jugadores/ranking")
    public ResponseEntity<?> getAllPlayersOrderByScore() {
        List<ClasificacionDTO> allPlayers = jugadorRepo.findAll(Sort.by(Sort.Direction.DESC, "puntos"))
                .stream()
                .map(jugador -> new ClasificacionDTO(
                        jugador.getNombre(),
                        jugador.getPuntos(),
                        jugador.getAvatar(),
                        jugador.getEquipo() != null ? jugador.getEquipo().getNombre() : null))
                .collect(Collectors.toList());

        if (allPlayers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allPlayers);
    }

    @PostMapping("/jugadores")
    public ResponseEntity<?> addPlayer(@RequestBody NewJugadorDTO newPlayer){

        Jugador nuevoJugador = new Jugador();
        Equipo equipo =null; //empieza en null en caso de que no se proporcione id de equipo en el post se le asigna un equipo null
        if (newPlayer.getEquipo_id()!=null) {
            equipo = equipoRepo.findById(newPlayer.getEquipo_id()).orElse(null);
        }

        if(newPlayer.getNombre() != null  && newPlayer.getClave() != null){

            nuevoJugador.setNombre(newPlayer.getNombre());
            nuevoJugador.setEquipo(equipo); //si el id de equipo no existe simplemente no se le añadira equipo, es decir se le introducirá el valor null en equipo
            nuevoJugador.setClave(newPlayer.getClave());
            nuevoJugador.setAvatar(newPlayer.getAvatar());
            //en el caso que no se pase admin, o se pase un valor incorrecto se le asignara el valor por defecto 0,(no admin)
            if (newPlayer.getAdmin() == 1 || newPlayer.getAdmin() == 0){
                nuevoJugador.setAdmin(newPlayer.getAdmin());
            }else{
                nuevoJugador.setAdmin(0);
            }
            nuevoJugador.setAdmin(newPlayer.getAdmin());
            nuevoJugador.setPuntos(0);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(jugadorRepo.save(nuevoJugador));

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/jugadores/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id){
        Jugador deletedPlayer = jugadorRepo.findById(id).orElse(null);

        if(deletedPlayer == null){
            return ResponseEntity.notFound().build();
        }

        jugadorRepo.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(deletedPlayer);
    }

    //Utilizo new player dto aqui tambien, ya que realmente serian los mismos datos, y asi evito crear una clase para nada, de momento al menos
    @PutMapping("jugadores/{id}")
    public ResponseEntity<?> updateTeam(@RequestBody NewJugadorDTO modifiedData, @PathVariable Long id){
        Jugador modifiedPlayer = jugadorRepo.findById(id).orElse(null);

        //En el caso que no exista el jugador que se quiera modificar se terminara la peticion con un not found
        if(modifiedPlayer == null){
            return ResponseEntity.notFound().build();
        }

        if(modifiedData.getEquipo_id() != null){
            Equipo team = equipoRepo.findById(modifiedData.getEquipo_id()).orElse(null);
            //aqui solo modificamos el equipo si existe, debajo gestiono la opcion de que se elimine el equipo del jugador si se le pasa 0 como equipo_id
            if(team != null){
                modifiedPlayer.setEquipo(team);
            }
        }
        //aqui compruebo para eliminar el equipo del jugador se debe enviar especificamente un 0 en el id del equipo
        if(modifiedData.getEquipo_id()==0){
            Equipo equipoNull=null;
            modifiedPlayer.setEquipo(equipoNull);
        }


        if (modifiedData.getAdmin() == 0 || modifiedData.getAdmin() == 1)
            modifiedPlayer.setAdmin(modifiedData.getAdmin());

        if (modifiedData.getNombre() != null)
            modifiedPlayer.setNombre(modifiedData.getNombre());

        if (modifiedData.getClave() != null)
            modifiedPlayer.setClave(modifiedData.getClave());

        if (modifiedData.getAvatar() != null)
            modifiedPlayer.setAvatar(modifiedData.getAvatar());

        if (modifiedData.getPuntos() != null)
            modifiedPlayer.setPuntos((modifiedData.getPuntos().intValue()));

        jugadorRepo.save(modifiedPlayer);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(modifiedPlayer);
    }
}
