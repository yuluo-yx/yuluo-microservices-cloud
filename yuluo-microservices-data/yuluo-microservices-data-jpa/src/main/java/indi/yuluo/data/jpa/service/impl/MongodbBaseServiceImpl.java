package indi.yuluo.data.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

import indi.yuluo.data.jpa.repository.MongodbBaseRepository;
import indi.yuluo.data.jpa.service.MongodbBaseService;

/**
 * @author: yuluo
 * @date: 2023/5/15 15:59
 * @description: Mongodb Base ServiceImpl
 */

public class MongodbBaseServiceImpl<T, ID extends Serializable, JPA extends MongodbBaseRepository<T, ID>>
		implements MongodbBaseService<T, ID, JPA> {


	@Autowired
	private JPA jpa;


	public T save(T t) {
		return this.jpa.save(t);
	}


	public T update(T t) {
		return this.jpa.save(t);
	}

	public List<T> findAll() {

		return (List)this.jpa.findAll();
	}

    public Page<T> findAll(Pageable pageable) {

		return this.jpa.findAll(pageable);
    }

	@Override
	public T findById(ID id) {

		return this.jpa.findById(id).orElse(null);
	}

	public boolean deleteById(ID id) {
		try{
			this.jpa.deleteById(id);
			return true;
		}catch (Exception e){
			return false;
		}
	}

}
