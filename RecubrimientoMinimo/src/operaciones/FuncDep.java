package operaciones;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class FuncDep {

	public static class Builder {
		private Set<Atributos> left;
		private Set<Atributos> right;

		public Builder() {
			this.left = new HashSet<>();
			this.right = new HashSet<>();
		}

		public FuncDep build() {
			return new FuncDep(this.left, this.right);
		}

		public Builder left(Atributos... as) {
			this.left.addAll(Arrays.asList(as));
			return this;
		}

		public Builder left(Set<Atributos> as) {
			this.left.addAll(as);
			return this;
		}

		public Builder right(Atributos... as) {
			this.right.addAll(Arrays.asList(as));
			return this;
		}

		public Builder right(Set<Atributos> as) {
			this.right.addAll(as);
			return this;
		}

	}

	public static Set<FuncDep> getSet(String exprs) {
		if (exprs.equals("")) {
			return new HashSet<>();
		}
		exprs = exprs.replaceAll("\\s+", "");
		return getSet(exprs.split(";"));
	}

	public static Set<FuncDep> getSet(String[] exprs) {
		Set<FuncDep> fds = new HashSet<>();
		for (String s : exprs) {
			fds.add(FuncDep.of(s));
		}
		return fds;
	}

	public static FuncDep of(String expr) {
		String[] halves = expr.split("-->");
		return of(halves[0], halves[1]);
	}

	public static FuncDep of(String left, String right) {
		left = left.replaceAll("\\s+", "");
		right = right.replaceAll("\\s+", "");
		String[] lefts = left.split(",");
		String[] rights = right.split(",");
		Builder bd = new Builder();
		for (String s : lefts) {
			bd.left(Atributos.of(s));
		}
		for (String s : rights) {
			bd.right(Atributos.of(s));
		}
		return bd.build();
	}

	protected final Set<Atributos> left;

	protected final Set<Atributos> right;

	public FuncDep(Set<Atributos> left, Set<Atributos> right) {
		this.left = new HashSet<>(left);
		this.right = new HashSet<>(right);
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof FuncDep)) {
			return false;
		}
		FuncDep fd = (FuncDep) o;
		return this.left.equals(fd.left) && this.right.equals(fd.right);
	}

	public Set<Atributos> getLeft() {
		return new HashSet<>(this.left);
	}

	public Set<Atributos> getRight() {
		return new HashSet<>(this.right);
	}

	@Override
	public int hashCode() {
		int result = 17;
		for (Atributos at : this.left) {
			result = 31 * result + at.hashCode();
		}
		for (Atributos at : this.right) {
			result = 31 * result + at.hashCode();
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder((this.left.size() + this.right.size()) * Atributos.AVERAGE_LENGTH);
		for (Atributos at : this.left) {
			sb.append(at.toString());
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(" --> ");
		for (Atributos at : this.right) {
			sb.append(at.toString());
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		return sb.toString();
	}

}
