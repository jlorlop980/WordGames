# API Word Games
## Entrega 2 (Crud Partida y datos de juego)
Primera entrega del trabajo de los juegos de palabras en la que se encuentra el crud de las entidades partida y jugador.
### *Jugador*
#### EndPoints
| Endpoint             | Resultado                                        | Método |
|----------------------|--------------------------------------------------|:------:|
| `/jugadores/ranking` | Muestra todos los jugadores ordenador por puntos |  GET   |


Se ha añadido el endpoint para ordenar los jugadores por puntuación

-------
### *Juego*
#### *EndPoints*
| Endpoint       | Resultado                            | Método |
|----------------|--------------------------------------|:------:|
| `/juegos`      | Muestra todos los juegos             |  GET   |
| `/juegos/{id}` | Mostrar un único juego por su `{id}` |  GET   |

---------
### *Partida*
#### *EndPoints*
| Endpoint          | Resultado                           | Método |
|-------------------|-------------------------------------|:------:|
| `/partidas`       | Muestra todas las partidas          |  GET   |
| `/partidas/{id}`  | Muestra una partida por su `{id}`   |  GET   |
| `/partidas`       | Crear una partida                   |  POST  |
| `/partidas/{id}`  | Eliminar un partida                 | DELETE |



### *Datos importantes*
- Ya se encuentra el archivo application.properties solo es necesaria cambiar la añadir la contraseña
- El puerto usado es el 3009
- Los peticiones de Insomnia están en está misma entrega (Anteriores + las nuevas)
- Los scripts .sql con la base de datos y los datos de prueba están en esta entrega con solo datos de prueba de juego y partida

