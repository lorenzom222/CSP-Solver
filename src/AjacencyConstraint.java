import java.util.HashMap;

public class AjacencyConstraint extends BinaryConstraint {

	AjacencyConstraint(Variable var1, Variable var2) {
		super(var1, var2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean constraintSatisfied(HashMap<Variable, Value> assingment) {
		

		if ((assingment.get(var1) == null) ||
				assingment.get(var2) == null ) {
			return true;
		}
		if (assingment.get(var1) == assingment.get(var2)) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "(" + this.var1 + ", " + this.var2 + ")";
	}
}