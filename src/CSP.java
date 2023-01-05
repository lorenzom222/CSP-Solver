
import java.util.*;

public class CSP {

	ArrayList<Value> domain;
	ArrayList<Variable> variables;
	HashMap<Variable, ArrayList<Constraint>> constraintMap;

	CSP(ArrayList<Value> domain, ArrayList<Variable> variables,
			HashMap<Variable, ArrayList<Constraint>> constraintMap) {
		this.domain = domain;
		this.variables = variables;
		this.constraintMap = constraintMap;
	}

	public ArrayList<Value> getDomain() {
		return domain;
	}

	public void setDomain(ArrayList<Value> domain) {
		this.domain = domain;
	}

	public ArrayList<Variable> getVariables() {
		return variables;
	}

	public void setVariables(ArrayList<Variable> variables) {
		this.variables = variables;
	}

	public HashMap<Variable, ArrayList<Constraint>> getConstraintMap() {
		return constraintMap;
	}

	public void setConstraintMap(HashMap<Variable, ArrayList<Constraint>> constraintMap) {
		this.constraintMap = constraintMap;
	}

	CSP() {

	}

	public LinkedHashMap<Variable, Value> backtrackingSearch() {
		LinkedHashMap<Variable, Value> map = new LinkedHashMap<Variable, Value>();
		for (Variable v : variables) {
			map.put(v, null);
		}
		return backtrack(map);
	}

	public LinkedHashMap<Variable, Value> backtrack(LinkedHashMap<Variable, Value> assingment) {

		if (isComplete(assingment)) {
			return assingment;
		}

		Variable unassigned = variables.stream().filter(t -> (assingment.get(t) == null)).findFirst().get();
		for (Value val : domain) {
			assingment.put(unassigned, val);
			if (isConsistnet(unassigned, assingment)) {
				LinkedHashMap<Variable, Value> result = new LinkedHashMap<Variable, Value>();
				result = backtrack(assingment);
				if (result != null) {
					return result;
				}
			} else { // Added to satisfy run out of domain error
				assingment.put(unassigned, null);

			}
		}

		return null;
	}

	boolean isComplete(HashMap<Variable, Value> assingment) {

		for (Variable v : variables) {
			if ((assingment.get(v) == null)) {
				return false;

			}
		}
		return true;

	}

	public boolean isConsistnet(Variable variable, HashMap<Variable, Value> assignment) {
		for (Constraint constraint : constraintMap.get(variable)) {
			if (!constraint.constraintSatisfied(assignment)) {
				return false;
			}
		}
		return true;
	}

}