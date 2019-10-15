package com.finartz.hrtaskapp.db.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finartz.hrtaskapp.model.entity.Task;
@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task,Integer>{

}
