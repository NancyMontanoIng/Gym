package com.example.reto3mangym.Service;

import com.example.reto3mangym.Model.Machine;
import Repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    public List<Machine> getAll() {
        return machineRepository.getAll();
    }
    public Optional<Machine> getMachine(int id){
        return machineRepository.getMachine(id);

    }

    public Machine save(Machine machine){
        if(machine.getId() == null){
            return machineRepository.save(machine);
        } else  {
            Optional<Machine> machineEncontrado = machineRepository.getMachine(machine.getId());
            if(machineEncontrado.isPresent()){
                return machineRepository.save(machine);
            } else{
                return machine;
            }
        }
    }

    public Machine update(Machine machine) {
        if (machine.getId() != null) {
            Optional<Machine> machineEncontrado = machineRepository.getMachine(machine.getId());
            if(!machineEncontrado.isPresent()){
                if(machine.getBrand() != null){
                    machineEncontrado.get().setBrand(machine.getBrand());
                }
                if(machine.getName() != null){
                    machineEncontrado.get().setName((machine.getName()));
                }
                if(machine.getYear() != null){
                    machineEncontrado.get().setYear((machine.getYear()));
                }
                if(machine.getDescription() != null){
                    machineEncontrado.get().setDescription((machine.getDescription()));
                }
                if(machine.getCategory() != null){
                    machineEncontrado.get().setCategory((machine.getCategory()));
                }
                //return machineRepository.save(machineEncontrado.get());
                machineRepository.save(machineEncontrado.get());
                return machineEncontrado.get();
            }else{
                return  machine;
            }
        }else {
            return machine;
        }
    }

    public boolean delete(int Id){
        Boolean resultado = getMachine(Id).map(PorEliminar -> {
            machineRepository.delete(PorEliminar);
            return true;
        }).orElse(false);
        return resultado;
    }

}
