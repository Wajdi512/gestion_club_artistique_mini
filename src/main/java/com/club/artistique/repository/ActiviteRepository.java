package com.club.artistique.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.club.artistique.entities.Activite;
import com.club.artistique.forms.SearchForm;

@Repository
public class ActiviteRepository extends BaseJPARepository<Activite, Integer> {

	public ActiviteRepository(EntityManager em) {
		super(Activite.class, em);
	}

	@SuppressWarnings("unchecked")
	public List<Activite> findActiviteBySearchCriteria(final SearchForm search) {
		final StringBuilder sb = new StringBuilder("SELECT act FROM Activite act ");
		if(search != null) {
			if (StringUtils.hasLength(search.getNomCoach())) {
				sb.append("JOIN act.coachs coachs ");
				sb.append("WHERE 1=1 ");
				sb.append("AND coachs.nom like :coach ");
			} else {
				sb.append("WHERE 1=1 ");
			}

			if (StringUtils.hasLength(search.getTitreActivite())) {
				sb.append("AND act.titre like :titre ");
			}
		}
		final Query query = em.createQuery(sb.toString(), Activite.class);
		if(search != null) {
			if (StringUtils.hasLength(search.getTitreActivite())) {
				query.setParameter("titre", "%" + search.getTitreActivite() + "%");
			}
			if (StringUtils.hasLength(search.getNomCoach())) {
				query.setParameter("coach", "%" + search.getNomCoach() + "%");
			}
		}
		return query.getResultList();
	}

}
