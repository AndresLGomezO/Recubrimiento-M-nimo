package unitest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import operaciones.Atributos;
import operaciones.FuncDep;
import operaciones.Operaciones;

public class TestCasesProyeccion {


	
	private Set<FuncDep> testE1 = new HashSet<>();
	private Set<Atributos> testAttr1 = new HashSet<>();

	@Before
	public void test1SetUp() {
		testE1 = FuncDep.getSet("");
		testAttr1 = Atributos.getSet("A,E,B");	

	}

	@Test
	public void test1Proyeccion() {
		assertEquals(Operaciones.Proyeccion(testAttr1),testE1);
		
	}

	
}
