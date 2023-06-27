package DAO_Interfaces;

import java.util.List;

import models.GradeHoliday;
import models.Holiday;

public interface HolidayDAO {

	/**
	 * Retrieves a list of all holidays.
	 *
	 * @return A list of all holidays.
	 */
	List<Holiday> findAllHolidays();

	/**
	 * Finds a holiday by its ID.
	 *
	 * @param id The ID of the holiday.
	 * @return The holiday with the specified ID.
	 */
	GradeHoliday findHolidayById(String id);

	/**
	 * Retrieves a list of all grade holidays.
	 *
	 * @return A list of all grade holidays.
	 */
	List<GradeHoliday> findAllGradeHolidays();
}
