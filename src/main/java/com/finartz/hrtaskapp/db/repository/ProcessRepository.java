package com.finartz.hrtaskapp.db.repository;

import com.finartz.hrtaskapp.model.entity.Process;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessRepository extends JpaRepository<Process , Integer> {
}
