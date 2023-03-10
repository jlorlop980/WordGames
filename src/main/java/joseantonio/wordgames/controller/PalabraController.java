package joseantonio.wordgames.controller;

import joseantonio.wordgames.model.Scrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class PalabraController {

    //todas las palabras
    @GetMapping("/palabras")
    public ResponseEntity<?> getAllWords() throws Exception{

        Scrapper lector = new Scrapper();

        List<String> allWords = lector.leer("static/diccionario.txt");

        if (allWords.isEmpty()){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allWords);

    }

    //palabra alatoria (solo 1)
    @GetMapping("/palabras/aleatoria")
    public ResponseEntity<?> getRandomWord() throws Exception {
        Scrapper lector = new Scrapper();

        List<String> allWords = lector.leer("static/diccionario.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Random random = new Random();

        int randomIndex = random.nextInt(allWords.size()); // Generar índice aleatorio

        return ResponseEntity.ok(allWords.get(randomIndex));
    }

    //palabras aleatorias segun el numero indicado por el usuario
    @GetMapping("/palabras/aleatoria/{cantidad}")
    public ResponseEntity<?> getMultipleRandomWords(@PathVariable int cantidad) throws Exception{

        List<String> randomWords = new ArrayList<>();

        Scrapper lector = new Scrapper();

        List<String> allWords = lector.leer("static/diccionario.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Random random = new Random();

        for(int i = 0; i < cantidad; i++){
            int randomIndex = random.nextInt(allWords.size());
            randomWords.add(allWords.get(randomIndex));
        }

        return ResponseEntity.ok(randomWords);

    }

    //palabras que empiezan de la forma indicada en la peticion
    @GetMapping("/palabras/empiezan/{cadena}")
    public ResponseEntity<?> getWordsStartingWith(@PathVariable String cadena) throws Exception {
        Scrapper lector = new Scrapper();
        List<String> allWords = lector.leer("static/diccionario.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> wordsStartingWith = new ArrayList<>();

        for (String word : allWords) {
            if (word.startsWith(cadena)) {
                wordsStartingWith.add(word);
            }
        }

        return ResponseEntity.ok(wordsStartingWith);
    }

    //palabras que terminan de forma indicada en la peticion
    @GetMapping("/palabras/terminan/{cadena}")
    public ResponseEntity<?> getWordsEndingWith(@PathVariable String cadena) throws Exception {
        Scrapper lector = new Scrapper();
        List<String> allWords = lector.leer("static/diccionario.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> wordsEndingWith = new ArrayList<>();

        for (String word : allWords) {
            if (word.endsWith(cadena)) {
                wordsEndingWith.add(word);
            }
        }

        return ResponseEntity.ok(wordsEndingWith);
    }

    //palabras que contienen los caracteres indicados en la peticion
    @GetMapping("/palabras/contienen/{cadena}")
    public ResponseEntity<?> getWordsContaining(@PathVariable String cadena) throws Exception {
        Scrapper lector = new Scrapper() ;
        List<String> allWords = lector.leer("static/diccionario.txt");

        if (allWords.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<String> palabrasCoincidentes = new ArrayList<>();


        for (String word : allWords) {
            if (word.contains(cadena)) {
                palabrasCoincidentes.add(word);
            }
        }

        return ResponseEntity.ok(palabrasCoincidentes);
    }

}
