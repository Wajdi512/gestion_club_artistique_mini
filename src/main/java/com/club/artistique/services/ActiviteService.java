package com.club.artistique.services;

import java.util.List;

import com.club.artistique.entities.Activite;
import com.club.artistique.forms.SearchForm;

/**
 * @author Wajdi
 *
 */
public interface ActiviteService extends AbstractBaseService<Activite, Integer> {

	List<Activite> findActiviteBySearchCriteria(final SearchForm search);

}
