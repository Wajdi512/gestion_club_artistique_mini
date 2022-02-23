package com.club.artistique.forms;

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
public class ActiviteForm {
	private Integer id;
	private String titre;
	private String description;
	private String image;
    private MultipartFile[] fileDatas;
}
