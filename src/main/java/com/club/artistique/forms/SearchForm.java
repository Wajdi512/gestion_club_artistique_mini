package com.club.artistique.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wajdi
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchForm {
	private String titreActivite;
	private String nomCoach;
	private String photo;
}
