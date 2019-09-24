package com.buildweek.foodie.repository;

import com.buildweek.foodie.models.Reviews;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewsRepository extends CrudRepository<Reviews, Long>
{
    @Transactional
    @Modifying
    @Query(value = " INSERT INTO reviews(restid, reviewid) values (:restid, :reviewid) ", nativeQuery = true)
    void insertRestaurantToReview(long restid, long reviewid);
}
