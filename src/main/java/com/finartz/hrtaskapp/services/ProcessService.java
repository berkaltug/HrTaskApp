package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Process;

public interface ProcessService {

    Process getProcess(Integer id);
    Process saveProcess(Process process);
    Process updateProcess(Process newProcess);
    void deleteProcess(Integer id);

}
