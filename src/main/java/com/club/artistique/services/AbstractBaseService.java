package com.club.artistique.services;

import java.util.List;
import java.util.Optional;

/**
 * @author Wajdi
 *
 * @param <T>
 * @param <ID>
 */
public interface AbstractBaseService<T, ID> {

	T saveOrUpdate(T t);

	void deleteById(ID id);

	List<T> findAll();

	Optional<T> findById(ID id);
	
	List<T> findAllByIds(Iterable<ID> ids);

}
