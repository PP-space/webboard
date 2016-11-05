package com.webboard.models;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Table;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
//    @Query(value = "select p from PostVO p limit :start , :end ")
    default  List<PostVO> findInIdRange(@Param("start") int start, @Param("end") int end) {
        SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("select p from PostVO");
        q.setFirstResult(start);
        q.setMaxResults(end);
        return q.list();
    }

    @Modifying
//    @Query(value = "update PostVO p set view_count = view_count +1 where  id_post = :id")
    default int updateViewCount(@Param("id") int id){
        SessionFactory sessionFactory = new Configuration().configure().
                buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("update PostVO p set view_count = view_count +1 where  id_post = :id");
        return id;
    }

}
