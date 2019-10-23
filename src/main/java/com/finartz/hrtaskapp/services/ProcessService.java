package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.dto.ProcessDto;
import com.finartz.hrtaskapp.model.entity.Process;
import org.springframework.data.domain.Page;

public interface ProcessService {

    Page<ProcessDto> getAllProcess(int page) throws Exception;
    Process getProcess(Integer id) throws Exception;
    Process addProcess(Process process) throws Exception;
    Process updateProcess(Process newProcess) throws Exception;
    void deleteProcess(Integer id) throws Exception;

}
