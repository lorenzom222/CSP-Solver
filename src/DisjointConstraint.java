import java.util.HashMap;

public class DisjointConstraint extends BinaryConstraint {

	Variable v1;
	Variable v2;

	DisjointConstraint(Variable v1, Variable v2) {
		super(v1, v2);
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public boolean constraintSatisfied(HashMap<Variable, Value> assingment) {

		if (assingment.get(var1) == null || assingment.get(var2) == null) {
			return true;
		}

		int a1 = Integer.parseInt(assingment.get(var1).getInt());

		int a2 = Integer.parseInt(assingment.get(var2).getInt());

		if ((a1 <= a2 && a1 + v1.time >= a2) || (a1 <= a2 + v2.time && a1 + v1.time >= a2 + v2.time)) {
			return false;
		}
		return true;
	}

}
