# API Word Games
## Entrega 3 (Peticiones de palabras)
Ultima entrega con las peticiones de las palabras
### *Palabras*
#### EndPoints
| Endpoint                         | Resultado                                                | Método |
|----------------------------------|----------------------------------------------------------|:------:|
| `/palabras`                      | Muestra todas las palabras                               |  GET   |
| `/palabras/aleatoria`            | Devuelve una palabra aleatoria                           |  GET   |
| `/palabras/aleatoria/{cantidad}` | Devuelve un numero indicado de palabras aleatorias       |  GET   |
| `/palabras/terminan/{cadena}`    | Muestra las palabras que acaban con la cadena indicada   |  GET   |
| `/palabras/empiezan/{cadena}`    | Muestra las palabras que empiezan con la cadena indicada |  GET   |
| `/palabras/contienen/{cadena}`   | Muestra las palabras que contienen una cadena  indicada  |  GET   |


### *Datos importantes*
- Ya se encuentra el archivo application.properties solo es necesaria cambiar la añadir la contraseña
- El puerto usado es el 3009
- Los peticiones de Insomnia están en está misma entrega (Solo palabras ya que al no alterar nada de las otras no creo que haga falta)
- Las palabras se encuentran en la carpeta resources static.

