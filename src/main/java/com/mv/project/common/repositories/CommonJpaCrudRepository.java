package com.mv.project.common.repositories;

import java.io.Serializable;
import java.util.List;

import com.mv.project.common.constants.ProjectConstant;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.mv.project.common.entities.BaseEntity;

@NoRepositoryBean
public interface CommonJpaCrudRepository<T extends BaseEntity, ID extends Serializable> extends CrudRepository<T, ID> {

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Query("select e from #{#entityName} e where e.isDeleted = '" + ProjectConstant.Flag.N + "'")
    List<T> findAll();

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
   /* @Query("select count(1) from #{#entityName} e where e.isDeleted = '" + ProjectConstant.Flag.N + "'")
    long count();*/
    @Modifying
    @Query(value = "update  #{#entityName} e set e.isDeleted = 'Y' where e.id= ?1 ")
    void delete(long id);
}