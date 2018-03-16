package unitest;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import operaciones.*;

import org.junit.Before;
import org.junit.Test;

public class TestCases {
	private Set<FuncDep> testI1, testE1, testI2, testE2, testI3, testE3, testI4, testE4 = new HashSet<>();

	@Before
	public void test1SetUp() {
		testE1 = FuncDep.getSet(
				"C, F --> B; B, E --> C; C --> A; D --> E; D --> F; C, F --> D; A, B --> C; B, C --> D; A, C, D --> E; A, C, D --> BC");
		testI1 = FuncDep.getSet("A,B-->C; D-->E,F; C-->A;" + " B,E-->C; B,C-->D;" + " C,F-->B,D; A,C,D-->BC,E-->A,F");
		testI2 = FuncDep
				.getSet("A,B --> C; D --> E,F; C --> A; B,E --> C; B,C --> D; C,F --> B,D; A,C,D --> B; C,E --> A,F");
		testE2 = FuncDep.getSet(
				"A,B --> C; D --> E; D --> F; C --> A; B,E --> C; B,C --> D; C,F --> D; C,F --> B; A,C,D-->B; C,E --> A; C,E --> F");
		testI3 = FuncDep.getSet("A-->B,C; B-->E; C,D-->E,F");
		testE3 = FuncDep.getSet("A-->B; A-->C; B-->E; C,D-->E; C,D-->F");
		testI4 = FuncDep.getSet("A,B-->C; B,D-->F,G; A,E-->C; C-->D,E; C-->E");
		testE4 = FuncDep.getSet("A,B-->C; B,D-->F; B,D-->G; A,E-->C; C-->D; C-->E");

	}

	@Test
	public void test1() {
		assertEquals(Operaciones.l0(testI1), testE1);
	}

	@Test
	public void test2() {
		assertEquals(Operaciones.l0(testI2), testE2);
	}

	@Test
	public void test3() {
		assertEquals(Operaciones.l0(testI3), testE3);
	}

	@Test
	public void test4() {
		assertEquals(Operaciones.l0(testI4), testE4);
	}

}
