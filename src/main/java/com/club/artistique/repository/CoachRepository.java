package com.club.artistique.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.club.artistique.entities.Coach;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Integer>{

}
