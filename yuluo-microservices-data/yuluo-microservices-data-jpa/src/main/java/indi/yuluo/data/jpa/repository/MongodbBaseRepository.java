package indi.yuluo.data.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author: yuluo
 * @date: 2023/5/15 15:59
 * @description: Mongodb Base Repository
 */

@NoRepositoryBean
public interface MongodbBaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID> {

	@Override
	Page<T> findAll(Pageable pageable);

}
