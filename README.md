# API Word Games
## Entrega 1 (Crud Jugadores y Equipo)
Primera entrega del trabajo de los juegos de palabras en la que se encuentra el crud de las entidades partida y jugador.
### *Jugador*
#### EndPoints
| Endpoint          | Resultado                                   | Método |
|-------------------|---------------------------------------------|:------:|
| `/jugadores`      | Muestra todos los jugadores                 |  GET   |
| `/jgadores/{id}`  | Mostrar el jugador a partir de un id `{id}` |  GET   |
| `/jugadores`      | Añadir un jugador                           |  POST  |
| `/jugadores/{id}` | Eliminar un jugador                         | DELETE |
| `/jugadores/{id}` | Modificar un jugador                        |  PUT   |

#### *Control de errores*
- Se controla si el objeto está completo es decir, tiene todos los campos necesario, (los puntos siempre son 0 al crearse ya que un jugador nuevo no tendra puntos)
- A la hora del delete se comprueba primero si existe el jugador a borrar
- A la hora de buscar por id primero se comprueba si existe
- No es necesario el avatar a la hora de crear el jugador

-------
### *Equipo*
#### *EndPoints*
| Endpoint          | Resultado                                    | Método  |
|-------------------|----------------------------------------------|:-------:|
| `/equipos`        | Muestra todos los equipos                    |   GET   |
| `/equipos/{id}`   | Mostrar el equipo a partir de un id `{id}`   |   GET   |
| `/equipos`        | Añadir un equipo                             |  POST   |
| `/equipos/{id}`   | Eliminar un equipo                           | DELETE  |
| `/equipos/{id}`   | Modificar un equipo                          |   PUT   |


#### *Control de errores*
- Se controla si el objeto está completo es decir, tiene todos los campos necesario, (los puntos siempre son 0 al crearse ya que un equipo nuevo no tendra puntos)
- A la hora del delete se comprueba primero si existe el equipo a borrar
- A la hora de buscar por id primero se comprueba si existe
- Los puntos por defecto son 0 al crear un equipo y se puede crear sin necesidad de un logo para el equipo

---------
### *Datos importantes*
- El .sql se encuentra en la primera primera entrega (modelo para juego de palabras)
- Ya se encuentra el archivo application.properties solo es necesaria cambiar la añadir la contraseña
- El puerto usado es el 3009
- Los peticiones de Insomnia están en la ultima entrega
- Los datos de prueba estan en la entrega del modelo

