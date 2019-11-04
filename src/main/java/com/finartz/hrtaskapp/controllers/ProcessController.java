package com.finartz.hrtaskapp.controllers;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.ProcessDto;
import com.finartz.hrtaskapp.model.entity.Process;
import com.finartz.hrtaskapp.services.ProcessService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Destination;
import java.util.Optional;

@RestController
@RequestMapping(value="/process")
public class ProcessController {

    private ProcessService processService;
    private ModelMapper modelMapper;

    @Autowired
    public ProcessController(ProcessService processService, ModelMapper modelMapper) {
        this.processService = processService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllProcess(@RequestParam("page") Integer page){
        Optional<Page<ProcessDto>> optionalProcesses= processService.getAllProcess(page);
        if(!optionalProcesses.isPresent()){
            return new ResponseEntity<>(ResponseMessage.NOVALUE.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(optionalProcesses.get(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProcess(@PathVariable("id") Integer processId){
        Optional<Process> optionalProcess=processService.getProcess(processId);
        if(!optionalProcess.isPresent()){
            return new ResponseEntity<>(ResponseMessage.NOVALUE.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(modelMapper.map(optionalProcess.get(),ProcessDto.class),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProcess(@RequestBody ProcessDto processDto){
        Optional<Process> optionalProcess=processService.addProcess(modelMapper.map(processDto,Process.class));
        if(!optionalProcess.isPresent()){
            return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResponseMessage.ADDED.get(),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProcess(@RequestBody ProcessDto processDto,@PathVariable("id")Integer processId){
        //not the perfect way about creationDate
        Process process=modelMapper.map(processDto,Process.class);
        processService.getProcess(processId).ifPresent(p->process.setCreationDate(p.getCreationDate()));
        process.setProcessId(processId);
        Optional<Process> optional=processService.updateProcess(process);
        if(!optional.isPresent()){
            return new ResponseEntity<>(ResponseMessage.EDITERROR.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResponseMessage.UPDATED.get(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProcess(@PathVariable("id")Integer processId){
        Optional<Process> optionalProcess=processService.deleteProcess(processId);
        if(!optionalProcess.isPresent()){
            return new ResponseEntity<>(ResponseMessage.DELETEERROR.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResponseMessage.DELETED.get(),HttpStatus.OK);
    }
}
