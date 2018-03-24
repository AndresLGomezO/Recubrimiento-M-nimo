package operaciones;

import java.util.HashSet;
import java.util.Set;

public final class Atributos {

	protected static final int AVERAGE_LENGTH = 10;

	
	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public static Set<Atributos> getSet(String nombre) {
		if (nombre.equals("")) {
			return new HashSet<>();
		}
		nombre = nombre.replaceAll("\\s+", "");
		return getSet(nombre.split(","));
	}
	public static Set<Set<Atributos>> getSetset(String[] arreglo) {
		Set<Set<Atributos>> attrs = new HashSet<>();
		for (String s : arreglo) {
			Set<Atributos> s1 = new HashSet<>();
			if (!s.equals("") && !s.equals(" ")) {
			s = s.replaceAll("\\s+", "");
			s1 = getSet(s.split(","));
			}
			attrs.add(s1);
		}
		return attrs;
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	
	public static Set<Atributos> getSet(String[] nombre) {
		Set<Atributos> attrs = new HashSet<>();
		for (String s : nombre) {
			attrs.add(Atributos.of(s));
		}
		return attrs;
	}

	/**
	 * 
	 * @param nombre
	 * @return
	 */
	public static Atributos of(String nombre) {
		return new Atributos(nombre);
	}

	private final String nombre;

	/**
	 * 
	 * @param nombre
	 */
	public Atributos(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Atributos)) {
			return false;
		}
		Atributos a = (Atributos) o;
		return a.nombre.equals(this.nombre);
	}

	public String getnombre() {
		return this.nombre;
	}

	@Override
	public int hashCode() {
		return this.nombre.hashCode();
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
