package DAO_Interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import models.EmployeeRefDocuments;

@Repository
public interface ReferenceDocumentDAOInterface {

	/**
	 * Finds an employee reference document by its ID.
	 *
	 * @param id The ID of the employee reference document.
	 * @return The employee reference document with the specified ID.
	 */
	EmployeeRefDocuments findById(String id);

	/**
	 * Saves an employee reference document into the database.
	 *
	 * @param document The employee reference document to be saved.
	 */
	void save(EmployeeRefDocuments document);

	/**
	 * Retrieves a list of all employee reference documents.
	 *
	 * @return A list of all employee reference documents.
	 */
	List<EmployeeRefDocuments> getAllDocs();

	/**
	 * Retrieves the current index value.
	 *
	 * @return The current index value.
	 */
	int getIndex();

	/**
	 * Deletes an employee reference document by its ID.
	 *
	 * @param id The ID of the employee reference document to be deleted.
	 */
	void deleteById(int id);

}
