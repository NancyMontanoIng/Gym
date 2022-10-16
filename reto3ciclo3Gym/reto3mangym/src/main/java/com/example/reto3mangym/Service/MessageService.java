package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Message;
import com.example.reto3mangym.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return (List<Message>) messageRepository.getAll();
    }
    public Optional<Message> getMessage(int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message message){
        if(message.getIdMessage() == null){
            if(message.getMessageText().length()<=250){
                return messageRepository.save(message);
            } else {
                return message;
            }

        } else  {
            Optional<Message> messageEncontrado = messageRepository.getMessage(message.getIdMessage());
            if(messageEncontrado.isPresent()){
                return messageRepository.save(message);
            } else{
                return message;
            }
        }
    }

    public Message update(Message message) {
        if (message.getIdMessage() != null) {
            Optional<Message> messageEncontrado = messageRepository.getMessage(message.getIdMessage());
            if(!messageEncontrado.isPresent()){
                if(message.getMessageText() != null){
                    messageEncontrado.get().setMessageText(message.getMessageText());
                }
                return messageRepository.save(messageEncontrado.get());
            }
        }
        return message;
    }

    public boolean delete(int Id){
        Boolean resultado = getMessage(Id).map(PorEliminar -> {
            messageRepository.delete(PorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}
