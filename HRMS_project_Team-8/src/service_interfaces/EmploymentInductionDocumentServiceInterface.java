package service_interfaces;

import java.util.List;

import models.EmploymentInductionDocument;
import models.input.output.EmploymentInductionDocumentViewModel;

public interface EmploymentInductionDocumentServiceInterface {
	/**
	 * Adds an employment induction document to the system.
	 *
	 * @param document The employment induction document to be added.
	 */
	public void addEmploymentInductionDocument(EmploymentInductionDocument document);

	/**
	 * Retrieves the file associated with the employment induction document based on the document index.
	 *
	 * @param documentIndex The index of the employment induction document.
	 * @return The file associated with the document as a string.
	 */
	public String getEmploymentInductionDocumentFile(int documentIndex);

	/**
	 * Retrieves all employment induction documents in the system.
	 *
	 * @return A list of employment induction document view models.
	 */
	public List<EmploymentInductionDocumentViewModel> getAllDocuments();
}
