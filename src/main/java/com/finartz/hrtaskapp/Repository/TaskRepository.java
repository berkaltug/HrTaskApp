package com.finartz.hrtaskapp.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finartz.hrtaskapp.Entity.Task;
@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task,Integer>{

}
