import java.util.HashMap;
import java.util.HashSet;

public class BeforeConstraint extends BinaryConstraint {

	Variable var1;
	Variable var2;
	HashSet<Value> domain;

	BeforeConstraint(Variable var1, Variable var2) {
		super(var1, var2);
		this.var1 = var1;
		this.var2 = var2;

	}

	public HashSet<Value> getDomain() {
		return domain;
	}

	public void setDomain(HashSet<Value> domain) {
		this.domain = domain;
	}

	@Override
	public boolean constraintSatisfied(HashMap<Variable, Value> assingment) {

		if (assingment.get(var1) == null || assingment.get(var2) == null) {
			return true;
		}
		int a1 = Integer.parseInt(assingment.get(var1).getInt());

		int a2 = Integer.parseInt((assingment.get(var2).getInt()));

		if (a2 >= a1 + var1.time) {
			return true;
		}
		return false;
	}

}
