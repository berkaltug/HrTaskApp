package com.finartz.hrtaskapp.services.impl;

import com.finartz.hrtaskapp.db.repository.ProcessRepository;
import com.finartz.hrtaskapp.model.dto.ProcessDto;
import com.finartz.hrtaskapp.model.entity.Process;
import com.finartz.hrtaskapp.services.ProcessService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Page<ProcessDto> getAllProcess(int page) throws Exception {
        Pageable pageable= PageRequest.of(page,5, Sort.by("processId"));
        return processRepository
                .findAll(pageable)
                .map( process -> modelMapper.map( process ,ProcessDto.class));
    }

    @Override
    public Process getProcess(Integer id) throws Exception {
        return processRepository.findById(id).get();
    }

    @Override
    public Process addProcess(Process process) throws Exception{
        process.setCreationDate(new Date());
        return processRepository.save(process);
    }

    @Override
    public Process updateProcess(Process process) throws Exception {
        process.setUpdateDate(new Date());
        return processRepository.save(process);
    }

    @Override
    public void deleteProcess(Integer id) throws Exception {
        processRepository.delete(processRepository.findById(id).get());
    }
}
