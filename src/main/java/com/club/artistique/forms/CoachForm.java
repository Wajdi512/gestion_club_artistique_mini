package com.club.artistique.forms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
public class CoachForm {
	protected Integer id;
	protected String nom;
	protected String prenom;
	protected String bio;
	private List<Integer> lesActivites = new ArrayList<>(0);
	private MultipartFile[] fileDatas;
}
