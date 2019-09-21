package com.buildweek.foodie.repository;

import com.buildweek.foodie.models.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface ReviewsRepository extends CrudRepository<Reviews, Long>
{
}
