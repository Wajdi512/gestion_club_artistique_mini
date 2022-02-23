package com.club.artistique.repository;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * @author Wajdi
 *
 * @param <T>
 * @param <ID>
 */
public abstract class BaseJPARepository<T, ID> extends SimpleJpaRepository<T, ID> {

	protected EntityManager em;

	public BaseJPARepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

}
