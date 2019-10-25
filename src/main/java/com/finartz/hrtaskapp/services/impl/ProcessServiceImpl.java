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
import java.util.Optional;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<Page<ProcessDto>> getAllProcess(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5, Sort.by("processId"));
        Page<ProcessDto> page = processRepository
                .findAll(pageable)
                .map(process -> modelMapper.map(process, ProcessDto.class));
        return Optional.ofNullable(page);
    }

    @Override
    public Optional<Process> getProcess(Integer id)  {
        return processRepository.findById(id);
    }

    @Override
    public Optional<Process> addProcess(Process process) {
        process.setCreationDate(new Date());
        return Optional.ofNullable(processRepository.save(process));
    }

    @Override
    public Optional<Process> updateProcess(Process process){
        process.setUpdateDate(new Date());
        return Optional.ofNullable(processRepository.save(process));
    }

    @Override
    public Optional<Process> deleteProcess(Integer id)  {
        Optional<Process> optionalProcess=processRepository.findById(id);
        optionalProcess.ifPresent(process -> processRepository.delete(process));
        return optionalProcess;
    }
}
