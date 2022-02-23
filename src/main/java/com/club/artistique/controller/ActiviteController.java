package com.club.artistique.controller;

import static com.club.artistique.constants.Constants.UPLOAD_IMAGE_DIR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.club.artistique.entities.Activite;
import com.club.artistique.forms.ActiviteForm;
import com.club.artistique.forms.SearchForm;
import com.club.artistique.services.ActiviteService;

/**
 * @author Wajdi
 *
 */
@Controller
@RequestMapping("/activites")
public class ActiviteController {

	private Logger logger = LoggerFactory.getLogger(ActiviteController.class);

	@Autowired
	private ActiviteService activiteService;

	@GetMapping
	public String index(Model model, @ModelAttribute SearchForm searchForm) {
		model.getAttribute("titreActivite");
		model.addAttribute("searchCriteria", new SearchForm());
		model.addAttribute("newActivite", new ActiviteForm());
		model.addAttribute("activites", activiteService.findActiviteBySearchCriteria(searchForm));
		model.addAttribute("searchCriteria", searchForm);
		return "list-activites";
	}

	@PostMapping
	public String addActivite(@ModelAttribute ActiviteForm form) {
		final Activite activite = new Activite();
		activite.setId(form.getId());
		activite.setTitre(form.getTitre());
		activite.setDescription(form.getDescription());
		if (form.getFileDatas() != null && form.getFileDatas().length > 0) {
			final String fileName = form.getFileDatas()[0].getOriginalFilename();
			activite.setImage(form.getFileDatas()[0].getOriginalFilename());
			Path path = Paths.get(UPLOAD_IMAGE_DIR, fileName);
			try {
				Files.write(path, form.getFileDatas()[0].getBytes());
				logger.info("File copied succesfully");
			} catch (IOException e) {
				logger.error("Cannot write file", e);
			}
		}
		activiteService.saveOrUpdate(activite);
		return "redirect:/activites";
	}

	@GetMapping("/delete/{id}")
	public String deleteActivite(@PathVariable Integer id) {
		activiteService.deleteById(id);
		return "redirect:/activites";
	}

	@GetMapping("/edit/{id}")
	public String editActivite(@PathVariable Integer id, Model model) {
		final Optional<Activite> activiteOpt = activiteService.findById(id);
		if (!activiteOpt.isPresent()) {
			model.addAttribute("errorMessage", "Activité non trouvée");
			return "error";
		}
		final ActiviteForm activiteForm = new ActiviteForm();
		activiteForm.setId(activiteOpt.get().getId());
		activiteForm.setTitre(activiteOpt.get().getTitre());
		activiteForm.setDescription(activiteOpt.get().getDescription());
		activiteForm.setImage(activiteOpt.get().getImage());
		model.addAttribute("newActivite", activiteForm);
		return "add-activities";
	}

}
