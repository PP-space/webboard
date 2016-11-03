package com.webboard.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Sorravit on 3/11/2559.
 */
@Transactional
public interface  UserDAO extends CrudRepository<UserVO,Long>{
    public UserVO findById(Long id);
}
