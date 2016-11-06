package com.webboard.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
//    @Query(value = "select p from PostVO p where p.id_post between :start AND :end ")
    default List<PostVO> findInIdRange(@Param("start") int start, @Param("end") int end){
        Configuration c = new Configuration();
        c.configure("/hibernate.cfg.xml");
        SessionFactory sf = c.buildSessionFactory();
        Session s = sf.openSession();
        org.hibernate.Query q = s.createQuery("from PostVO");
        q.setFirstResult(start);
        q.setMaxResults(10);
        List<PostVO> result = q.list();
        return  result;
    }
    @Modifying
    @Query(value = "update PostVO p set p.view_count = p.view_count +1 where  p.id_post = :id")
    int updateViewCount(@Param("id") int id);
    @Modifying
    @Query(value = "update PostVO p set p.data = :data where  p.id_post = :id")
    int updatePost(@Param("id") int id, @Param("data") String data);

}
