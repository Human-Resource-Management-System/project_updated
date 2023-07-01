package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import DAO_Interfaces.HolidayDAO;
import models.GradeHoliday;
import models.Holiday;

@Repository
public class HolidayDAOImpl implements HolidayDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public List<Holiday> findAllHolidays() {
		TypedQuery<Holiday> query = entityManager.createQuery("SELECT h FROM Holiday h ORDER BY h.hday_date ASC",
				Holiday.class);
		return query.getResultList();
	}

	@Override
	public GradeHoliday findHolidayById(String id) {
		return entityManager.find(GradeHoliday.class, id);
	}

	@Override
	@Transactional
	public List<GradeHoliday> findAllGradeHolidays() {
		TypedQuery<GradeHoliday> query = entityManager.createQuery("SELECT gh FROM GradeHoliday gh",
				GradeHoliday.class);
		return query.getResultList();
	}

}
