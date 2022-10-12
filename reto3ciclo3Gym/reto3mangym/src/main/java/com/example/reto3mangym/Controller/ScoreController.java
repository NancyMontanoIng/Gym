package com.example.reto3mangym.Controller;


import com.example.reto3mangym.Model.Score;
import com.example.reto3mangym.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
@RestController
@RequestMapping("/api/Score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    //ruta de peticion GET   /api/Score/all
    @GetMapping("/all")
    public List<Score> getAll() {
        return scoreService.getAll();
    }

    @GetMapping("/{id}")            // localhost:8080/api/Category/21
    public Optional<Score> getScore(@PathVariable("id") int id){
        return scoreService.getScore(id);
    }


    //ruta de peticion POST    /api/Score/save
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Score save(@RequestBody Score score){
        return scoreService.save(score);

    }

}
