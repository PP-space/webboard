package com.webboard.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Sorravit on 5/11/2559.
 */
@Transactional
public interface ReplyDAO extends CrudRepository<ReplyVO,Integer> {
    @Query(value = "select r from ReplyVO r where r.postId = :postId ")
    List<ReplyVO> findReplyByPost(@Param("postId") int postId);
}
