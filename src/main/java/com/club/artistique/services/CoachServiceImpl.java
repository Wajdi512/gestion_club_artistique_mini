package com.club.artistique.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.artistique.entities.Coach;
import com.club.artistique.repository.CoachRepository;

/**
 * @author Wajdi
 *
 */
@Service
public class CoachServiceImpl implements CoachService {

	@Autowired
	private CoachRepository coachRepository;

	@Override
	public Coach saveOrUpdate(Coach t) {
		return coachRepository.saveAndFlush(t);
	}

	@Override
	public void deleteById(Integer id) {
		coachRepository.deleteById(id);
	}

	@Override
	public List<Coach> findAll() {
		return coachRepository.findAll();
	}

	@Override
	public Optional<Coach> findById(Integer id) {
		return coachRepository.findById(id);
	}

	@Override
	public List<Coach> findAllByIds(Iterable<Integer> ids) {
		return coachRepository.findAllById(ids);
	}

}
