
package operaciones;

import java.util.HashSet;
import java.util.Set;

public class Operaciones {

	private Operaciones() {

	}

	public static Set<Atributos> cierre(Set<Atributos> attrs, Set<FuncDep> fds) {
		Set<Atributos> result = new HashSet<>(attrs);

		boolean found = true;
		while (found) {
			found = false;
			for (FuncDep fd : fds) {
				if (result.containsAll(fd.left) && !result.containsAll(fd.right)) {
					result.addAll(fd.right);
					found = true;
				}
			}
		}

		return result;
	}

}
