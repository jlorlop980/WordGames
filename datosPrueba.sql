INSERT INTO juego (nombre, instrucciones, intentosMax, dificultad)
VALUES ('Ahorcado', 'Adivina la palabra letra por letra', 7, 'Fácil');

INSERT INTO juego (nombre, instrucciones, intentosMax, dificultad)
VALUES ('Wordle', 'adivina la palabra en menos de 6 intntos', 6, 'Medio');

INSERT INTO juego (nombre, instrucciones, intentosMax, dificultad)
VALUES ('Scrabble', 'Crea la palabra más larga posible con las letras obtenidas', 1, 'Difícil');

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2023-01-01 14:30:00', 10, 'PERRO', 2, 1);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2023-01-02 16:45:00', 5, 'CAPIBARA', 1, 1);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2023-01-03 09:00:00', 10, 'RIO', 1, 2);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2023-01-04 13:15:00', 2, 'PUEBLO', 3, 2);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2023-01-05 17:30:00', 8, 'PANADERO', 1, 3);

INSERT INTO partida (date, puntos, palabra, jugador_id, juego_id)
VALUES ('2023-01-06 11:45:00', 5, 'RATON', 2, 3);