package com.finartz.hrtaskapp.controllers;

import com.finartz.hrtaskapp.controllers.response.ResponseMessage;
import com.finartz.hrtaskapp.model.dto.MetricDto;
import com.finartz.hrtaskapp.model.entity.Metric;
import com.finartz.hrtaskapp.services.MetricService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/metrics")
public class MetricController {


    private MetricService metricService;
    private ModelMapper modelMapper;

    public MetricController(MetricService metricService, ModelMapper modelMapper) {
        this.metricService = metricService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{page}")
    public ResponseEntity<?> getAllMetrics(@RequestParam("page") int page) {
        Optional<Page<MetricDto>> optionalMetrics;
        optionalMetrics = metricService.getAllMetrics(page).map(p->p.map(metric->modelMapper.map(metric,MetricDto.class)));
        if(!optionalMetrics.isPresent()){
            return new ResponseEntity<>(ResponseMessage.NOVALUE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(optionalMetrics.get(), HttpStatus.OK);
    }



}
