package com.proch.project.common.repository;

import com.proch.project.common.constant.ProjectConstant;
import com.proch.project.common.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonJpaCrudRepository<T extends BaseEntity, ID extends Serializable> extends CrudRepository<T, ID> {

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
   /* @Query("select e from #{#entityName} e where e.isDeleted = '" + ProjectConstant.Flag.N + "'")
    List<T> findAll();*/

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
   /* @Query("select count(1) from #{#entityName} e where e.isDeleted = '" + ProjectConstant.Flag.N + "'")
    long count();*/

}