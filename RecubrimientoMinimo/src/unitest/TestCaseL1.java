package unitest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import operaciones.FuncDep;
import operaciones.Operaciones;

public class TestCaseL1 {


	
	private Set<FuncDep> testI1, testE1, testI2, testE2, testI3, testE3, testI4, testE4 = new HashSet<>();

	@Before
	public void test1SetUp() {
		testE1 = FuncDep.getSet(
				"B-->C; A-->B; A-->C; A-->C; A-->D");
		testI1 = FuncDep.getSet("B-->C; A-->B; A-->C; A,B-->C; A,C-->D");
		
		
		testI2 = FuncDep
				.getSet("K-->J; C-->D; I-->J; J-->K; E-->J; D,K-->L");
		testE2 = FuncDep.getSet(
				"K-->J; C-->D; I-->J; J-->K; E-->J; D,K-->L");
		
		
		testI3 = FuncDep.getSet("E-->D; A-->B; A-->C; D,E-->A; D,E-->B; B,C-->E");
		testE3 = FuncDep.getSet("E-->D; A-->B; A-->C; B,C-->E; E-->A; E-->B;");
		
		
		testI4 = FuncDep.getSet("A1 --> B2; A1-->C3; A1-->D4; A1-->E5; A1-->H8; B2-->C3; B2-->A1");
		testE4 = FuncDep.getSet("A1 --> B2; A1-->C3; A1-->D4; A1-->E5; A1-->H8; B2-->C3; B2-->A1");
		


	}

	@Test
	public void test1L1() {
		assertEquals(Operaciones.getL1(testI1), testE1);
		
	}

	@Test
	public void test2L1() {
		assertEquals(Operaciones.getL1(testI2), testE2);
	}

	@Test
	public void test3L1() {
		assertEquals(Operaciones.getL1(testI3), testE3);
	}

	@Test
	public void test4L1() {
		assertEquals(Operaciones.getL1(testI4), testE4);
	}
}
