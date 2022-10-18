package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Score;
import com.example.reto3mangym.Repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll() {
        return scoreRepository.getAll();
    }
    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }

    public Score save(Score score){
        if(score.getIdScore() == null){
            if(score.getMessageText().length()<=250){
                return scoreRepository.save(score);
            } else {
                return score;
            }

        } else  {
            Optional<Score> scoreEncontrado = scoreRepository.getScore(score.getIdScore());
            if(scoreEncontrado.isPresent()){
                return scoreRepository.save(score);
            } else{
                return score;
            }
        }
    }

    public Score update(Score score) {
        if (score.getIdScore() != null) {
            Optional<Score> scoreEncontrado = scoreRepository.getScore(score.getIdScore());
            if(!scoreEncontrado.isPresent()){
                if(score.getMessageText() != null){
                    scoreEncontrado.get().setMessageText(score.getMessageText());
                }
                if(score.getStars() != null){
                    scoreEncontrado.get().setStars((score.getStars()));
                }
                return scoreRepository.save(scoreEncontrado.get());
            }

        }
        return score;
    }

    public boolean delete(int Id){
        Boolean resultado = getScore(Id).map(PorEliminar -> {
            scoreRepository.delete(PorEliminar);
            return true;
        }).orElse(false);
        return resultado;

    }
}
