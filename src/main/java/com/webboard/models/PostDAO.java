package com.webboard.models;

import org.hibernate.annotations.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Sorravit on 3/11/2559.
 */

@Transactional
public interface PostDAO extends CrudRepository<PostVO,Integer> {
    @Query(value = "select p from PostVO p where p.idPost between :start AND :end ")
    List<PostVO> findInIdRange(@Param("start") int start, @Param("end") int end);
    @Modifying
    @Query(value = "update PostVO p set p.viewCount = p.viewCount +1 where  p.idPost = :id")
    int updateViewCount(@Param("id") int id);
    @Modifying
    @Query(value = "update PostVO p set p.data = :data where  p.idPost = :id")
    int updatePost(@Param("id") int id, @Param("data") String data);

}
