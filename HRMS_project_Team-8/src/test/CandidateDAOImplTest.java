package test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import DAO.CandidateDAOImpl;
import models.Candidate;
import models.Eofr;
import models.HRDepartment;
import models.Inductiondocuments;

public class CandidateDAOImplTest {

	@Mock
	private EntityManager entityManager;

	@InjectMocks
	private CandidateDAOImpl candidateDAO;

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveCandidate() {
		Candidate candidate = new Candidate();
		doNothing().when(entityManager).persist(candidate);

		candidateDAO.saveCandidate(candidate);

		verify(entityManager, times(1)).persist(candidate);
	}

	@Test
	public void testGetCandidateById() {
		int candidateId = 1;
		Candidate expectedCandidate = new Candidate();
		when(entityManager.find(Candidate.class, candidateId)).thenReturn(expectedCandidate);

		Candidate candidate = candidateDAO.getCandidateById(candidateId);

		verify(entityManager, times(1)).find(Candidate.class, candidateId);
		assertEquals(candidate, expectedCandidate);
	}

	@Test
	public void testGetAllCandidates() {
		List<Candidate> expectedCandidates = new ArrayList<>();
		expectedCandidates.add(new Candidate());
		expectedCandidates.add(new Candidate());
		TypedQuery<Candidate> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT c FROM Candidate c", Candidate.class)).thenReturn(query);
		when(query.getResultList()).thenReturn(expectedCandidates);

		List<Candidate> candidates = candidateDAO.getAllCandidates();

		verify(entityManager, times(1)).createQuery("SELECT c FROM Candidate c", Candidate.class);
		verify(query, times(1)).getResultList();
		assertEquals(candidates, expectedCandidates);
	}

	@Test
	public void testFindAllIssuedCandidates() {
		List<Candidate> expectedCandidates = new ArrayList<>();
		expectedCandidates.add(new Candidate());
		expectedCandidates.add(new Candidate());
		TypedQuery<Candidate> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT c FROM Candidate c WHERE c.candStatus = :status", Candidate.class))
				.thenReturn(query);
		when(query.setParameter("status", "NA")).thenReturn(query);
		when(query.getResultList()).thenReturn(expectedCandidates);

		List<Candidate> candidates = candidateDAO.findAllIssuedCandidates();

		verify(entityManager, times(1)).createQuery("SELECT c FROM Candidate c WHERE c.candStatus = :status",
				Candidate.class);
		verify(query, times(1)).setParameter("status", "NA");
		verify(query, times(1)).getResultList();
		assertEquals(candidates, expectedCandidates);
	}

	@Test
	public void testInsertEofrInto() {
		Eofr eofr = new Eofr();
		doNothing().when(entityManager).persist(eofr);

		candidateDAO.insertEofrInto(eofr);

		verify(entityManager, times(1)).persist(eofr);
	}

	@Test
	public void testGetLatestEofrIdFromDatabase() {
		Long expectedEofrId = 1L;
		TypedQuery<Long> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT MAX(e.eofr_id) FROM Eofr e", Long.class)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(expectedEofrId);

		Long eofrId = candidateDAO.getLatestEofrIdFromDatabase();

		verify(entityManager, times(1)).createQuery("SELECT MAX(e.eofr_id) FROM Eofr e", Long.class);
		verify(query, times(1)).getSingleResult();
		assertEquals(eofrId, expectedEofrId);
	}

	@Test
	public void testGetAllDocuments() {
		List<String> expectedDocuments = new ArrayList<>();
		expectedDocuments.add("Document 1");
		expectedDocuments.add("Document 2");
		TypedQuery<String> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT e.idty_title FROM Inductiondocuments e", String.class))
				.thenReturn(query);
		when(query.getResultList()).thenReturn(expectedDocuments);

		List<String> documents = candidateDAO.getAllDocuments();

		verify(entityManager, times(1)).createQuery("SELECT e.idty_title FROM Inductiondocuments e", String.class);
		verify(query, times(1)).getResultList();
		assertEquals(documents, expectedDocuments);
	}

	@Test
	public void testGetHrById() {
		int hR_id = 1;
		HRDepartment expectedHRDepartment = new HRDepartment();
		TypedQuery<HRDepartment> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT hr FROM HRDepartment hr WHERE hr.employeeId = :id", HRDepartment.class))
				.thenReturn(query);
		when(query.setParameter("id", hR_id)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(expectedHRDepartment);

		HRDepartment hrDepartment = candidateDAO.getHrById(hR_id);

		verify(entityManager, times(1)).createQuery("SELECT hr FROM HRDepartment hr WHERE hr.employeeId = :id",
				HRDepartment.class);
		verify(query, times(1)).setParameter("id", hR_id);
		verify(query, times(1)).getSingleResult();
		assertEquals(hrDepartment, expectedHRDepartment);
	}

	@Test
	public void testFindAllProvidedCandidates() {
		List<Candidate> expectedCandidates = new ArrayList<>();
		expectedCandidates.add(new Candidate());
		expectedCandidates.add(new Candidate());
		TypedQuery<Candidate> query = mock(TypedQuery.class);
		when(entityManager.createQuery("SELECT c FROM Candidate c WHERE c.candStatus = :status", Candidate.class))
				.thenReturn(query);
		when(query.setParameter("status", "AC")).thenReturn(query);
		when(query.getResultList()).thenReturn(expectedCandidates);

		List<Candidate> candidates = candidateDAO.findAllProvidedCandidates();

		verify(entityManager, times(1)).createQuery("SELECT c FROM Candidate c WHERE c.candStatus = :status",
				Candidate.class);
		verify(query, times(1)).setParameter("status", "AC");
		verify(query, times(1)).getResultList();
		assertEquals(candidates, expectedCandidates);
	}

	@Test
	public void testGetMaxDocIndexFromDatabase() {
		int expectedMaxDocIndex = 1;
		TypedQuery<Integer> query = mock(TypedQuery.class);
		when(entityManager.createQuery(
				"SELECT MAX(e.empoff.eofdDocIndex) FROM empoffdocuments e WHERE e.empoff.eofrId = :eofdId",
				Integer.class)).thenReturn(query);
		when(query.setParameter("eofdId", 1)).thenReturn(query);
		when(query.getSingleResult()).thenReturn(expectedMaxDocIndex);

		Integer maxDocIndex = candidateDAO.getMaxDocIndexFromDatabase();

		verify(entityManager, times(1)).createQuery(
				"SELECT MAX(e.empoff.eofdDocIndex) FROM empoffdocuments e WHERE e.empoff.eofrId = :eofdId",
				Integer.class);
		verify(query, times(1)).setParameter("eofdId", 0);
		verify(query, times(1)).getSingleResult();
		assertEquals(maxDocIndex, expectedMaxDocIndex);
	}

	private List<Inductiondocuments> getInductionDocuments() {
		List<Inductiondocuments> inductionDocuments = new ArrayList<>();
		inductionDocuments.add(new Inductiondocuments());
		return inductionDocuments;
	}
}
