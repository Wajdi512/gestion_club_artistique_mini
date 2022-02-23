package com.club.artistique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.artistique.entities.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Integer>{

}
