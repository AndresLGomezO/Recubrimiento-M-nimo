
package operaciones;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import DatosXpath.NodosXPath;

public class Operaciones {

	public static Set<Set<Atributos>> candid = new HashSet<Set<Atributos>>();
	public static Set<Set<Atributos>> Cierres = new HashSet<Set<Atributos>>();

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
		System.out.println("1. Conjunto Inicial L1" + fds);
		Set<FuncDep> result = new HashSet<>();
		result.addAll(fds);
		for (FuncDep fd : fds) {
			System.out.println("Iteracion " + fd);
			Set<Atributos> extranos = new HashSet<>();
			if (fd.getLeft().size() > 1) {
				Set<Set<Atributos>> Comb = Operaciones.reduction(fd.getLeft(), fds);
				for (Set<Atributos> Comb1 : Comb) {
					System.out.println("Combinacion " + Comb1 + " de " + fd);
					if (Operaciones.cierre(Comb1, result).containsAll(fd.getRight())) {
						Set<Atributos> temp = new HashSet<>();
						temp.addAll(fd.getLeft());
						temp.removeAll(Comb1);
						extranos.addAll(temp);
						System.out.println("Conjunto Extraños " + extranos);
					}
				}
			}
			System.out.println("Comparacion extranos " + extranos + " ^ " + fd.getLeft());
			if (!extranos.isEmpty()) {
				if (extranos.containsAll(fd.getLeft())) {
					System.out.println("Se elimina " + fd);
					result.remove(fd);
				} else {
					Set<Atributos> temp1 = new HashSet<>();
					temp1.addAll(fd.getLeft());
					temp1.removeAll(extranos);
					FuncDep add = new FuncDep(temp1, fd.getRight());
					result.remove(fd);
					result.add(add);
					System.out.println("Se agrega " + add);
				}
			}

		}

		return result;

	}

	public static Set<FuncDep> getL1A(Set<FuncDep> fds) {
		System.out.println("1. Conjunto Inicial L1" + fds);
		Set<FuncDep> result = new HashSet<>();
		for (FuncDep fd : fds) {
			if (fd.getLeft().size() > 1) {
				List<Atributos> leftList = new ArrayList<>();
				HashSet<Atributos> extranos = new HashSet<>();
				boolean noExtrano = false;
				for (Atributos a : fd.getLeft()) {
					// Validación atributos extraños
					Set<Atributos> rightAttribute = new HashSet<>(fd.getRight());
					noExtrano = false;
					Set<Atributos> newLeft = new HashSet<>();
					for (Atributos b : fd.getLeft()) {
						if (!b.equals(a) && !extranos.contains(b)) {
							newLeft.add(b);
						}
					}
					Set<Atributos> newLeftSideSet = new HashSet<Atributos>(newLeft);
					Set<Atributos> cierreSet = cierre(newLeftSideSet, fds);
					// Si el atributo a la der hace parte del cierre
					if (cierreSet == null || !cierreSet.contains(rightAttribute)) {
						// No extraÃ±o
						leftList.add(a);
						noExtrano = true;
					}
					if (!noExtrano) {
						// Atributo extraÃ±o
						extranos.add(a);
					}
				}
				if (leftList.size() < fd.left.size()) {
					if (!leftList.isEmpty()) {
						Set<Atributos> minLeftAttributes = new HashSet<Atributos>(leftList);
						FuncDep newDF = new FuncDep(minLeftAttributes, fd.right);
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

			} else {
				if (!result.contains(fd)) {
					result.add(fd);
				}
			}
		}
		System.out.println("1. Conjunto final L1" + result);
		return result;
	}

	public static Set<FuncDep> l0(Set<FuncDep> fds) {
		Set<FuncDep> toRemove = new HashSet<>();
		Set<FuncDep> toAdd = new HashSet<>();

		for (FuncDep fd : fds) {
			if (fd.getRight().size() > 1) {
				for (Atributos a : fd.getRight()) {
					Set<Atributos> der = new HashSet<>();
					der.add(a);
					if (!fd.getLeft().equals(der)) {
						toAdd.add(new FuncDep.Builder().left(fd.getLeft()).right(a).build());
					}

				}
				toRemove.add(fd);
			}
		}
		fds.addAll(toAdd);
		fds.removeAll(toRemove);
		return fds;

	}

	public static Set<FuncDep> getl2(Set<FuncDep> fds) {
		System.out.println("1. Conjunto Inicial L2" + fds);
		Set<FuncDep> FINAL = new HashSet<>();
		FINAL.addAll(fds);
		for (FuncDep fd : fds) {
			Set<FuncDep> toRemove = new HashSet<FuncDep>(FINAL);
			System.out.println("1. Conjunto Inicial " + toRemove);
			toRemove.remove(fd);
			System.out.println("Conjunto Eliminado " + toRemove);
			System.out.println("Cierre sin " + fd);
			if (Operaciones.cierre(fd.getLeft(), toRemove).containsAll(fd.getRight())) {
				System.out.println("Implicane " + fd.getLeft());
				System.out.println("Cierre " + Operaciones.cierre(fd.getLeft(), toRemove));
				System.out.println("Dependencia Eliminada " + fd);
				FINAL.remove(fd);
			}
		}
		System.out.println("1. Conjunto Final L2" + FINAL);
		return FINAL;
	}

	public static Set<Set<Atributos>> TodasLasLlaves(Set<FuncDep> dfs) {
		System.out.println("TODAS LAS LLAVES  ");
		Set<Atributos> Atb = new HashSet<>();
		Set<Atributos> Der = new HashSet<>();
		Set<Atributos> Falt = new HashSet<>();
		Falt.addAll(NodosXPath.attrs);
		Iterator<FuncDep> fd = dfs.iterator();
		while (fd.hasNext()) {
			FuncDep i = fd.next();
			Atb.addAll(i.getLeft());
		}
		Iterator<FuncDep> fi = dfs.iterator();
		while (fi.hasNext()) {
			FuncDep e = fi.next();
			Der.addAll(e.getRight());
		}
		Falt.removeAll(Der);
		Falt.removeAll(Atb);

		System.out.println("Conjunto Atributos Obligatorios " + Falt);
		System.out.println("Conjunto Implicantes " + Atb);
		Set<Set<Atributos>> all = Operaciones.reduction(Atb, dfs);
		all.add(Atb);
		System.out.println("All " + all);
		Set<Set<Atributos>> keys = new HashSet<Set<Atributos>>();
		for (Set<Atributos> It2 : all) {
			Set<Atributos> n = new HashSet<>();
			n.addAll(It2);
			n.addAll(Falt);
			System.out.println("Conjunto Agrandado " + n);
			if (Operaciones.cierre(n, dfs).containsAll(NodosXPath.attrs)) {
				System.out.println("Llave agregada " + n);
				keys.add(n);
			}
		}
		/*
		 * Iterator<Set<Atributos>> It2 = all.iterator();
		 * 
		 * while (It2.hasNext()) { System.out.println("Has Next "); Set<Atributos> n =
		 * new HashSet<>(); n.addAll(It2.next()); n.addAll(Falt);
		 * System.out.println("Conjunto Agrandado " + n); if(Operaciones.cierre(n,
		 * dfs).containsAll(NodosXPath.attrs)) { System.out.println("Llave agregada " +
		 * n); keys.add(n); }
		 * 
		 * }
		 */

		keys.add(NodosXPath.attrs);
		System.out.println("Conjunto Final Llaves " + keys);
		return keys;
	}

	public static Set<Set<Atributos>> reduction(Set<Atributos> Atribut, Set<FuncDep> fds) {
		// returns a new set
		Set<Set<Atributos>> r = new HashSet<Set<Atributos>>();
		Set<Atributos> New = new HashSet<>();
		New.addAll(Atribut);
		for (Atributos It1 : New) {
			Set<Atributos> c = new HashSet<>();
			c.addAll(New);

			c.remove(It1);

			if (!c.isEmpty()) {
				r.add(c);

				Set<Set<Atributos>> re = new HashSet<Set<Atributos>>();
				re.addAll(Operaciones.reduction(c, fds));
				if (!r.containsAll(re)) {
					r.addAll(re);

				}
			}

		}
		/*
		 * Iterator<Atributos> It1 = New.iterator(); while(It1.hasNext()) {
		 * 
		 * Set<Atributos> c = new HashSet<>(); c.addAll(New);
		 * 
		 * c.remove(It1.next());
		 * 
		 * if (!candid.contains(c)) { if(!c.isEmpty()){ r.add(c); candid.add(c);
		 * Set<Set<Atributos>> re = new HashSet<Set<Atributos>>();
		 * re.addAll(Operaciones.reduction(c,fds)); if(!r.containsAll(re)) {
		 * r.addAll(re); candid.addAll(re); } } }
		 * 
		 * }
		 */

		return r;
	}

	public static boolean EsLlaveCandidata(Set<Atributos> Attr, Set<FuncDep> Deps) {
		boolean flag = true;
		Set<Atributos> Orig = new HashSet<>();
		Orig.addAll(Attr);
		Iterator<Atributos> i = Attr.iterator();
		while (i.hasNext()) {
			Set<Atributos> Nueva = new HashSet<>();
			Nueva.addAll(Orig);
			Nueva.remove(i.next());
			if (Operaciones.cierre(Nueva, Deps).containsAll(NodosXPath.attrs)) {

				System.out.println("Llave candidata " + Attr);
				flag = false;

			}

		}

		return flag;
	}

	public static Set<Set<Atributos>> LlavesCandidatas(Set<FuncDep> dfs, Set<Atributos> Oblig) {
		Set<Set<Atributos>> sk = new HashSet<Set<Atributos>>();
		sk.addAll(Operaciones.TodasLasLlaves(dfs));
		System.out.println("Conjunto de Llaves " + sk);
		candid.clear();
		Set<Set<Atributos>> cand = new HashSet<Set<Atributos>>();
		Iterator<Set<Atributos>> i = sk.iterator();
		boolean flag = false;
		while (i.hasNext()) {
			flag = true;
			Set<Atributos> a = i.next();
			a.removeAll(Oblig);
			System.out.println("Iteracion " + a);
			Set<Set<Atributos>> c = Operaciones.reduction(a, dfs);
			for (Set<Atributos> j : c) {
				Set<Atributos> d = j;
				d.addAll(Oblig);
				System.out.println("Prueba Clave " + d + " de Iteracion " + a);
				if (!candid.contains(d)) {
					if (Operaciones.cierre(d, dfs).containsAll(NodosXPath.attrs)) {
						flag = false;

						System.out.println("Se elimina la clave " + a + " dado que " + d + " implica a "
								+ Operaciones.cierre(d, dfs));
					} else {
						System.out.println("Se agrega " + d + " implica " + Operaciones.cierre(d, dfs));
						candid.add(d);
					}

				} else {
					System.out.println("Se salta " + d);
				}

			}
			a.addAll(Oblig);
			/*
			 * Iterator<Set<Atributos>> j = c.iterator(); while(j.hasNext()){ Set<Atributos>
			 * d = j.next(); d.addAll(Oblig); System.out.println("Prueba Clave " + d
			 * +" de Iteracion " + a); if(Operaciones.cierre(d,
			 * dfs).containsAll(NodosXPath.attrs)) { flag = false; } }
			 */
			if (flag == true) {
				System.out.println("Llave agregada " + a);
				cand.add(a);
			}
		}
		return cand;
	}

	public static Set<FuncDep> Proyeccion(Set<Atributos> Atr) {
		Set<FuncDep> CubMin = getl2(getL1(l0(NodosXPath.fds)));
		Set<FuncDep> g = CubMin;
		Set<FuncDep> g1 = new HashSet<>();
		Set<Atributos> w = new HashSet<>();

		w.addAll(NodosXPath.attrs);
		w.removeAll(Atr);
		System.out.println("Conjunto G " + w);
		for (Atributos a : w) {
			Set<Atributos> ats = new HashSet<>();
			ats.add(a);
			System.out.println("Atributo A " + ats);
			Set<FuncDep> Hd = new HashSet<>();
			Set<FuncDep> Hi = new HashSet<>();
			Set<FuncDep> toAdd = new HashSet<>();
			for (FuncDep y : g) {
				if (y.getLeft().contains(a)) {
					Hi.add(y);
					System.out.println("Dependencia IZQ " + Hi);
				}
				if (y.getRight().contains(a) && y.getRight().size() == 1) {
					Hd.add(y);
					System.out.println("Dependencia DER " + Hd);
				}

			}
			if (!Hd.isEmpty()) {
				for (FuncDep Der : Hd) {
					if (!Hi.isEmpty()) {
						for (FuncDep Izq : Hi) {
							if (!Izq.getRight().equals(ats)) {
								Set<Atributos> IzqN = new HashSet<>();
								IzqN.addAll(Izq.getLeft());
								IzqN.remove(a);
								IzqN.addAll(Der.getLeft());
								FuncDep a1 = new FuncDep(IzqN, Izq.getRight());
								if (a1.getLeft().equals(a1.getRight())) {
									System.out.println(a1.getLeft() + " Es igual que " + a1.getRight());
								} else {
									toAdd.add(a1);
								}
							}
						}
					}
				}
			}
			g.removeAll(Hi);
			g.removeAll(Hd);
			g.addAll(toAdd);
			System.out.println("Nuevo G " + g);
		}

		return g;

	}

}
