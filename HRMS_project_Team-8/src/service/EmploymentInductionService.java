package service;

import org.springframework.beans.factory.annotation.Autowired;

import DAO_Interfaces.InductionDAO;
import service_interfaces.EmploymentInductionServiceInterface;

public class EmploymentInductionService implements EmploymentInductionServiceInterface {

	@Autowired
	private InductionDAO idao;// injecting DAO class object

	public Integer getid() {
		return idao.getIndex();
	}

	public Integer getidNext() {

		int i = idao.getIndex();
		return i + 1;
	}

}
