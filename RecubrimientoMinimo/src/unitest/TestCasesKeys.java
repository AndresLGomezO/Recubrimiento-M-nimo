package unitest;

import static org.junit.Assert.*;


import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import operaciones.Atributos;
import operaciones.FuncDep;
import operaciones.Operaciones;

public class TestCasesKeys {


	
	private Set<FuncDep> testdfs1 = new HashSet<>();
	private Set<Atributos> testOblig1, testTodos1 =  new HashSet<>();
	private Set<Set<Atributos>> testLlc, T1 = new HashSet<>();
	private String[] test = new String[2];
	private String[] test1 = new String[1];
	

	@Before
	public void test1SetUp() {
		testdfs1 = FuncDep.getSet("B-->C; A-->B; A-->D");
		testTodos1 = Atributos.getSet("A,B,C,D");
		testOblig1 = Atributos.getSet(" ");
		
		test[0] = "A";
		test[1] = "A,B";
		test1[0] = "A";
		testLlc = Atributos.getSetset(test);
		T1 = Atributos.getSetset(test1);
		
			}

	@Test
	public void test1LlavesCandidatas() {
		
		assertEquals(Operaciones.LlavesCandidatas(testLlc , testdfs1, testOblig1, testTodos1), T1);
		
		
	}


}
