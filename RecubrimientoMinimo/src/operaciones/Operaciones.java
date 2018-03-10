
package operaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	

	
	public static Set<FuncDep> getL1(Set<FuncDep> fds) {
		Set<FuncDep> result =   new HashSet<>();			
		for (FuncDep fd : fds) {
			if(fd.left.size()>1) {

				List<Atributos> leftList = new ArrayList<>();
				HashSet<Atributos> extranos = new HashSet<>();
				boolean noExtrano = false;
				for (Atributos a : fd.left) {

					// Validación atributos extraños

					List<Atributos> rightList = new ArrayList<>(fd.getRight());
					Atributos rightAttribute = rightList.get(0);

					noExtrano = false;
					List<Atributos> newLeft = new ArrayList<>();

					for (Atributos b : fd.left) {

						if (!b.equals(a) && !extranos.contains(b)) {

							newLeft.add(b);

						}

					}
					Set<Atributos> newLeftSideSet = new HashSet<Atributos>(newLeft);
					Set<Atributos> cierreSet = cierre(newLeftSideSet, fds);

					// Si el atributo a la der hace parte del cierre

					if (cierreSet == null || !cierreSet.contains(rightAttribute)) {

						// No extraño
						leftList.add(a);
						noExtrano = true;

					}

					if (!noExtrano) {
							//Atributo extraño
						extranos.add(a);
					}

				}

				if (leftList.size() < fd.left.size()) {

					if (!leftList.isEmpty()) {
						Set<Atributos> minLeftAttributes = new HashSet<Atributos>(leftList);
						FuncDep newDF = new FuncDep(minLeftAttributes,fd.right);

						// Evaluar nueva dependencia 

						Set<Atributos> verifyClosure = cierre(newDF.left, fds);
						List<Atributos> reducedRigth = new ArrayList<>(newDF.right);
						
						if (verifyClosure.contains(reducedRigth.get(0))) {
							if (!result.contains(newDF)) {
								result.add(newDF);
							}

						}

					}

				} else {
					if (!result.contains(fd)) {
						result.add(fd);
					}
				}

			}else {
				if (!result.contains(fd)) {
					result.add(fd);
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
