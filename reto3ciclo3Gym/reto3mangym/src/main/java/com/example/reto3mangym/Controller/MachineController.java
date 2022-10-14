package com.example.reto3mangym.Controller;

import com.example.reto3mangym.Model.Machine;
import com.example.reto3mangym.Service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api/Machine")
public class MachineController {
    @Autowired
    private MachineService machineService;

    //ruta de peticion GET   /api/Machine/all
    @GetMapping("/all")
    public List<Machine> getAll() {
        return machineService.getAll();
    }
    @GetMapping("/{id}")
    public Optional<Machine> getMachine(@PathVariable("id") int id){
        return machineService.getMachine(id);
    }


    //ruta de peticion POST    /api/Machine/save
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine save(@RequestBody Machine machine){
        return machineService.save(machine);
    }
    @PutMapping ("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine update(@RequestBody Machine machine) {
        return machineService.save(machine);
    }

    //@DeleteMapping("/{idMachine}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)




}
