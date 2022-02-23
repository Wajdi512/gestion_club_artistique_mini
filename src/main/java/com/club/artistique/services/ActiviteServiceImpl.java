package com.club.artistique.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.artistique.entities.Activite;
import com.club.artistique.forms.SearchForm;
import com.club.artistique.repository.ActiviteRepository;

/**
 * @author Wajdi
 *
 */
@Service
@Transactional
public class ActiviteServiceImpl implements ActiviteService {

	@Autowired
	private ActiviteRepository activiteRepository;

	@Override
	public Activite saveOrUpdate(Activite t) {
		return activiteRepository.saveAndFlush(t);
	}

	@Override
	public void deleteById(Integer id) {
		activiteRepository.deleteById(id);
	}

	@Override
	public List<Activite> findAll() {
		return activiteRepository.findAll();
	}

	@Override
	public Optional<Activite> findById(Integer id) {
		return activiteRepository.findById(id);
	}

	@Override
	public List<Activite> findActiviteBySearchCriteria(SearchForm search) {
		return activiteRepository.findActiviteBySearchCriteria(search);
	}

	@Override
	public List<Activite> findAllByIds(Iterable<Integer> ids) {
		return activiteRepository.findAllById(ids);
	}

}
