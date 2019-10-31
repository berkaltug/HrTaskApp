package com.finartz.hrtaskapp.controllers;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.FailReasonDto;
import com.finartz.hrtaskapp.model.entity.FailReason;
import com.finartz.hrtaskapp.services.FailReasonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/fails")
public class FailReasonController {

    @Autowired
    private FailReasonService failReasonService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllFails(@RequestParam("page") int page) {
        Optional<Page<FailReason>> optionalPage = failReasonService.getAllFails(page);
        if(!optionalPage.isPresent()){
            return new ResponseEntity<>(ResponseMessage.NOVALUE.get(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(optionalPage.get().map(failReasons -> modelMapper.map(failReasons,FailReasonDto.class)),HttpStatus.OK) ;
    }
}
