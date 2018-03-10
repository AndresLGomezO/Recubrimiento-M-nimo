
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

	public static Set<FuncDep> l0(Set<FuncDep> fds) {
		Set<FuncDep> toRemove = new HashSet<>();
		Set<FuncDep> toAdd = new HashSet<>();

		for (FuncDep fd : fds) {
			if (fd.right.size() > 1) {
				for (Atributos a : fd.right) {
					toAdd.add(new FuncDep.Builder().left(fd.left).right(a).build());
				}
				toRemove.add(fd);
			}
		}
		fds.addAll(toAdd);
		fds.removeAll(toRemove);
		
		return fds;

	}

}
