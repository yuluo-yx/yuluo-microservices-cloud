package indi.yuluo.data.jpa.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

import indi.yuluo.data.jpa.repository.MongodbBaseRepository;

public interface MongodbBaseService<T, ID extends Serializable, JPA extends MongodbBaseRepository<T, ID>> {

	T save(T t);

	T update(T t);

	Page<T> findAll(Pageable pageable);

	List<T> findAll();


	T findById(ID id);

	boolean deleteById(ID id);

}
