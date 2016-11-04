package com.webboard.models;

import org.hibernate.annotations.Table;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sorravit on 3/11/2559.
 */
@Transactional
public interface PostDAO extends CrudRepository<PostVO,Integer> {
    @Query(value = "select p from PostVO p where p.idPost between :start AND :end ")
    List<PostVO> findInIdRange(@Param("start") int start, @Param("end") int end);
    @Modifying
    @Query(value = "update PostVO p set view_count = view_count +1 where  id_post = :id")
    int updateViewCount(@Param("id") int id);

}
