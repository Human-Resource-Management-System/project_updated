package DAO_Interfaces;

import java.util.List;

import models.EmploymentInductionDocument;
import models.input.output.EmploymentInductionDocumentViewModel;

public interface EmploymentInductionDocumentDAO {

	/**
	 * Adds an employment induction document to the database.
	 *
	 * @param document The employment induction document to be added.
	 */
	void addEmploymentInductionDocument(EmploymentInductionDocument document);

	/**
	 * Retrieves an employment induction document by its index.
	 *
	 * @param documentIndex The index of the employment induction document.
	 * @return The employment induction document with the specified index.
	 */
	EmploymentInductionDocument getEmploymentInductionDocument(int documentIndex);

	/**
	 * Retrieves a list of all employment induction documents.
	 *
	 * @return A list of all employment induction documents.
	 */
	List<EmploymentInductionDocumentViewModel> getAllDocuments();

	// Other methods for updating and deleting EmploymentInductionDocuments can be added here
}
