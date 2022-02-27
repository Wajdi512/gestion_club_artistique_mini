package com.club.artistique.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.artistique.entities.Seance;
import com.club.artistique.repository.SeanceRepository;

/**
 * @author Wajdi
 *
 */
@Service
public class SeanceServiceImpl implements SeanceService {

	@Autowired
	private SeanceRepository seanceRepo;

	@Override
	public Seance saveOrUpdate(Seance t) {
		return seanceRepo.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		seanceRepo.deleteById(id);
	}

	@Override
	public List<Seance> findAll() {
		return seanceRepo.findAll();
	}

	@Override
	public Optional<Seance> findById(Integer id) {
		return seanceRepo.findById(id);
	}

	@Override
	public List<Seance> findAllByIds(Iterable<Integer> ids) {
		return seanceRepo.findAllById(ids);
	}

}
