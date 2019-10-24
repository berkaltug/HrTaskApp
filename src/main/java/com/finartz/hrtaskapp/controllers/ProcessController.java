package com.finartz.hrtaskapp.controllers;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.ProcessDto;
import com.finartz.hrtaskapp.model.entity.Process;
import com.finartz.hrtaskapp.services.ProcessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;
    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/")
    public ResponseEntity<?> getAllProcess(@RequestParam("page")int page)  {
        try{
            Page<ProcessDto> process=processService.getAllProcess(page);
            return new ResponseEntity<>(process,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause().getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{processId}")
    public ResponseEntity<?> getProcess(@PathVariable("processId") Integer processId)  {
        try {
            ProcessDto processDto=modelMapper.map(processService.getProcess(processId),ProcessDto.class);
            return new ResponseEntity<>( processDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{processId}/delete")
    public ResponseEntity<String> deleteProcess(@PathVariable("processId")Integer processId){
        try{
            processService.deleteProcess(processId);
            return new ResponseEntity<>(ResponseMessage.DELETED.get(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProcess(@RequestBody ProcessDto processDto)  {
        try {
            Process process = modelMapper.map(processDto, Process.class);
            processService.addProcess(process);
            return new ResponseEntity<>(process, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(ResponseMessage.ADDINGERROR.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProcess(@Valid @RequestBody ProcessDto processDto){
        try{
            Process process=modelMapper.map(processDto,Process.class);
            processService.updateProcess(process);
            return new ResponseEntity<>(process,HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(ResponseMessage.EDITERROR.get(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
