import java.util.*;

public abstract class UniaryConstraint extends Constraint{

	Variable  var; 
	

	UniaryConstraint(Variable var){
		this.var = var; 
	}
	
	@Override
	public abstract boolean constraintSatisfied(HashMap<Variable,Value> assingment);
		
	
}
