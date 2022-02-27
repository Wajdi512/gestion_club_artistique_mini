package com.club.artistique.controller;

import static com.club.artistique.constants.Constants.AJOUT_COACH;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.club.artistique.entities.Activite;
import com.club.artistique.entities.Coach;
import com.club.artistique.forms.CoachForm;
import com.club.artistique.forms.CoachProfil;
import com.club.artistique.services.ActiviteService;
import com.club.artistique.services.CoachService;

/**
 * @author Wajdi
 *
 */
@Controller
@RequestMapping("/coachs")
public class CoachController {

	private static final String ADD_EDIT_COACH = "add-edit-coach";

	private static final String LES_ACTIVITES = "lesActivites";

	private static final String NEW_COACH = "newCoach";

	@Autowired
	private CoachService coachService;

	@Autowired
	private ActiviteService activiteService;

	@GetMapping
	public String index(Model model) {
		model.addAttribute("lesCoachs", coachService.findAll());
		return "list-coachs";
	}

	@GetMapping("/add")
	public String showAddCoachForm(Model model) {
		model.addAttribute(NEW_COACH, new CoachForm());
		model.addAttribute(LES_ACTIVITES, activiteService.findAll());
		return ADD_EDIT_COACH;
	}

	@PostMapping("/add")
	public String addCoach(@ModelAttribute CoachForm form, Model model) {
		final Coach coach = new Coach();
		coach.setId(form.getId());
		coach.setNom(form.getNom());
		coach.setPrenom(form.getPrenom());
		if (!CollectionUtils.isEmpty(form.getLesActivites())) {
			coach.setMesActivites(activiteService.findAllByIds(form.getLesActivites()));
		}
		coachService.saveOrUpdate(coach);
		model.addAttribute(NEW_COACH, form);
		model.addAttribute("message", AJOUT_COACH);
		model.addAttribute(LES_ACTIVITES, activiteService.findAll());
		return ADD_EDIT_COACH;
	}

	@GetMapping("/edit/{id}")
	public String editCoach(@PathVariable Integer id, Model model) {
		final Optional<Coach> optCoach = coachService.findById(id);
		if (!optCoach.isPresent()) {
			model.addAttribute("errorMessage", "Activité non trouvée");
			return "error";
		}
		final CoachForm coachForm = new CoachForm();
		coachForm.setId(optCoach.get().getId());
		coachForm.setNom(optCoach.get().getNom());
		coachForm.setPrenom(optCoach.get().getPrenom());
		if (!CollectionUtils.isEmpty(optCoach.get().getMesActivites())) {
			coachForm.setLesActivites(
					optCoach.get().getMesActivites().stream().map(Activite::getId).collect(Collectors.toList()));
		}
		model.addAttribute(NEW_COACH, coachForm);
		model.addAttribute(LES_ACTIVITES, activiteService.findAll());
		return ADD_EDIT_COACH;
	}

	@GetMapping("/profil/{id}")
	public String shwoCoach(@PathVariable Integer id, Model model) {
		final Optional<Coach> optCoach = coachService.findById(id);
		if (!optCoach.isPresent()) {
			model.addAttribute("errorMessage", "Activité non trouvée");
			return "redirect:error";
		}
		final CoachProfil coachProfil = new CoachProfil();
		coachProfil.setId(id);
		coachProfil.setNom(optCoach.get().getNom());
		coachProfil.setPrenom(optCoach.get().getPrenom());
		coachProfil.setBio(optCoach.get().getBio());
		coachProfil.setListActivites(optCoach.get().getMesActivites());
		if (!StringUtils.hasLength(optCoach.get().getPhoto())) {
			coachProfil.setPhoto("coach-profil.png");
		} else {
			coachProfil.setPhoto(optCoach.get().getPhoto());
		}
		model.addAttribute("coach", coachProfil);
		return "show-coach-profil";
	}

	@GetMapping("/delete/{id}")
	public String deleteCoach(@PathVariable Integer id) {
		coachService.deleteById(id);
		return "redirect:/coachs";
	}

}
