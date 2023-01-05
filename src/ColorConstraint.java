import java.util.HashMap;
import java.util.HashSet;

public class ColorConstraint extends UniaryConstraint {

	Value v;
	boolean equals;

	ColorConstraint(Variable var, Value v, boolean e) {
		super(var);
		this.v = v;
		equals = e;

	}

	@Override
	public boolean constraintSatisfied(HashMap<Variable, Value> assingment) {

		Value color = assingment.get(var);
		String convertcolor = color.toString();
		String convertV = v.toString();

		if (equals == true) {
			if (!convertcolor.equals(convertV)) {
				return false;
			}
			return true;
		} else {
			if (convertcolor.equals(convertV)) {
				return false;
			}
			return true;
		}

	}

	public boolean returnE(boolean e) {
		return e;

	}

	@Override
	public String toString() {
		return "(" + this.var + ", " + this.v + ")";
	}

	public static void constraintSat(HashMap.Entry<String, HashSet<Constraint>> constraint) {

		System.out.println("Hey");

	}
}

class Helper {
	// To make class object in main
}