import java.util.HashMap;

public abstract class BinaryConstraint extends Constraint {

	Variable var1;
	Variable var2; 
	
	BinaryConstraint(Variable var1, Variable var2){
		this.var1 = var1; 
		this.var2 = var2;
	}
	
	@Override
	public abstract boolean constraintSatisfied(HashMap<Variable,Value> assingment);
	
}
