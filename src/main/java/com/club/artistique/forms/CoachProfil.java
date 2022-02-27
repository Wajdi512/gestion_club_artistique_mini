package com.club.artistique.forms;

import java.util.List;

import com.club.artistique.entities.Activite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachProfil extends CoachForm {
	private String photo;
	private List<Activite> listActivites;

}
