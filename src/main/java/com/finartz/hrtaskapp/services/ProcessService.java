package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.dto.ProcessDto;
import com.finartz.hrtaskapp.model.entity.Process;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProcessService {

    Optional<Page<ProcessDto>> getAllProcess(int page) ;
    Optional<Process> getProcess(Integer id);
    Optional<Process> addProcess(Process process);
    Optional<Process> updateProcess(Process newProcess);
    Optional<Process> deleteProcess(Integer id) ;

}
