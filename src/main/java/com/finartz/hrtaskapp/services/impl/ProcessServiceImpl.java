package com.finartz.hrtaskapp.services.impl;

import com.finartz.hrtaskapp.db.repository.ProcessRepository;
import com.finartz.hrtaskapp.model.entity.Process;
import com.finartz.hrtaskapp.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Override
    public Process getProcess(Integer id) {
        return processRepository.findById(id).get();
    }

    @Override
    public Process addProcess(Process process) {
        process.setCreationDate(new Date());
        return processRepository.save(process);
    }

    @Override
    public Process updateProcess(Process process) {
        process.setUpdateDate(new Date());
        return processRepository.save(process);
    }

    @Override
    public void deleteProcess(Integer id) {
        processRepository.delete(processRepository.getOne(id));
    }
}
