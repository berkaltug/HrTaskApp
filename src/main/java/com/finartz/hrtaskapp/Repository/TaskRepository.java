package com.finartz.hrtaskapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finartz.hrtaskapp.Entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>{

}
