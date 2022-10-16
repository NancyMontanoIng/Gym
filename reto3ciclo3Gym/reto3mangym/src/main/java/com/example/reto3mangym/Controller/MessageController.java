package com.example.reto3mangym.Controller;

import com.example.reto3mangym.Model.Machine;
import com.example.reto3mangym.Model.Message;
import com.example.reto3mangym.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/Message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //ruta de peticion GET   /api/Message/all
    @GetMapping("/all")
    public List<Message> getAll() {
        return messageService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessage(@PathVariable("id") int id){
        return messageService.getMessage(id);
    }


    //ruta de peticion POST    /api/Message/save
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody Message message){
        return messageService.save(message);

    }
    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message update(@RequestBody Message message) {
        return messageService.save(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return messageService.delete(id);
    }

}
