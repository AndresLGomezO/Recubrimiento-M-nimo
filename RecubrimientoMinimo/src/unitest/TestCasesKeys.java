package unitest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import operaciones.Atributos;
import operaciones.FuncDep;
import operaciones.Operaciones;

class TestCasesKeys {


	
	private Set<FuncDep> testdfs1 = new HashSet<>();
	private Set<Atributos> testOblig1, testTodos1, testExp1 = new HashSet<>();

	@Before
	public void test1SetUp() {
		testdfs1 = FuncDep.getSet("B-->C; A-->B; A-->C; A-->C; A-->D");
		testOblig1 = Atributos.getSet("A");
		testTodos1 = Atributos.getSet("B");
		
		
	}

	@Test
	public void test1LlavesCandidatas() {
		//equals((Operaciones.LlavesCandidatas(testdfs1, testOblig1,testOblig1)),testExp1);
			
		
	}


}
