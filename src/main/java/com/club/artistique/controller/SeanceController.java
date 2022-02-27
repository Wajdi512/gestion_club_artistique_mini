package com.club.artistique.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.club.artistique.entities.Activite;
import com.club.artistique.entities.Coach;
import com.club.artistique.entities.Seance;
import com.club.artistique.forms.SeanceForm;
import com.club.artistique.services.ActiviteService;
import com.club.artistique.services.CoachService;
import com.club.artistique.services.SeanceService;

/**
 * @author Wajdi
 *
 */
@Controller
@RequestMapping("/seances")
public class SeanceController {

	@Autowired
	private SeanceService seanceService;

	@Autowired
	private ActiviteService activiteService;

	@Autowired
	private CoachService coachService;

	@GetMapping
	public String showListSeance(Model model) {
		model.addAttribute("lesSeances", seanceService.findAll());
		return "list-seances";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("newSeance", new SeanceForm());
		loadActivitesAndCoachs(model);
		return "add-seance";
	}

	private void loadActivitesAndCoachs(Model model) {
		model.addAttribute("listActivites", activiteService.findAll());
		model.addAttribute("listCoach", coachService.findAll());
	}

	@PostMapping("/add")
	public String addSeance(@ModelAttribute SeanceForm form, Model model) {
		final Seance seance = new Seance();
		seance.setId(form.getId());
		final Optional<Activite> optAct = activiteService.findById(form.getActiviteId());
		if (!optAct.isPresent()) {
			return "redirect:error";
		}
		seance.setActivite(optAct.get());
		final Optional<Coach> optCoach = coachService.findById(form.getCoachId());
		if (!optCoach.isPresent()) {
			return "redirect:error";
		}
		seance.setCoach(optCoach.get());
		if (form.getStartTime() != null) {
			seance.setStartTime(form.getStartTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		}
		if (form.getEndTime() != null) {
			seance.setEndTime(form.getEndTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
		}
		seanceService.saveOrUpdate(seance);
		model.addAttribute("newSeance", form);
		loadActivitesAndCoachs(model);
		return "add-seance";
	}

	@GetMapping("/edit/{id}")
	public String editSeance(@PathVariable Integer id, Model model) {
		loadActivitesAndCoachs(model);
		final Optional<Seance> optSeance = seanceService.findById(id);
		if (!optSeance.isPresent()) {
			return "redirect:error";
		}
		model.addAttribute("newSeance",
				new SeanceForm(optSeance.get().getId(),
						Date.from(optSeance.get().getStartTime().atZone(ZoneId.systemDefault()).toInstant()),
						Date.from(optSeance.get().getEndTime().atZone(ZoneId.systemDefault()).toInstant()),
						optSeance.get().getCoach().getId(), optSeance.get().getActivite().getId()));

		return "add-seance";
	}

	@GetMapping("/delete/{id}")
	public String deleteSeance(@PathVariable Integer id) {
		seanceService.deleteById(id);
		return "redirect:/seances";
	}

}
