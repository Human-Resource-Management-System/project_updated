package service_interfaces;

public interface EmploymentInductionServiceInterface {

	/**
	 * Retrieves the next available ID for employment induction.
	 *
	 * @return The next available ID for employment induction.
	 */
	Integer getidNext();

	/**
	 * Retrieves the current ID for employment induction.
	 *
	 * @return The current ID for employment induction.
	 */
	Integer getid();

}
