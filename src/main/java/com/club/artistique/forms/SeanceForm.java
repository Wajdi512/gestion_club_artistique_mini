package com.club.artistique.forms;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeanceForm {

	private Integer id;
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date startTime;
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
	private Date endTime;
	private Integer coachId;
	private Integer activiteId;

}
