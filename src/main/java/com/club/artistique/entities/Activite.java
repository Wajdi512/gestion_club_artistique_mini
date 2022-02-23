package com.club.artistique.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Activite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titre;
	@Column(length = 255)
	private String description;
	private String image;
	@ManyToMany(mappedBy = "mesActivites")
	private List<Coach> coachs = new ArrayList<>();
	@OneToMany(mappedBy = "activite")
	private List<Seance> seances = new ArrayList<>();


}
