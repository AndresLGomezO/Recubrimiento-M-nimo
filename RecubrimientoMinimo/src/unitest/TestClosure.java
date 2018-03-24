package unitest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import operaciones.Atributos;
import operaciones.FuncDep;
import operaciones.Operaciones;

public class TestClosure {
	
	private Set<Atributos> testAtr, T1 = new HashSet<>();
	private Set<FuncDep> testDfs = new HashSet<>();
	
    @Before
	public void test1SetUp() {
		testDfs = FuncDep.getSet("B-->C; A-->B; A-->C; A-->C; A-->D");
		testAtr = Atributos.getSet("A");
		T1 = Atributos.getSet("A,B,C,D");


	}

	@Test
	public void testCierre() {
		assertEquals(Operaciones.cierre(testAtr, testDfs), T1);
		
	}

}
