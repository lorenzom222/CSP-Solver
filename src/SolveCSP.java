import java.io.*;
import java.util.*;

public class SolveCSP {

	public static void main(String args[]) {
		CSP csp = new CSP();
		readfile(csp);
		HashMap<Variable, Value> assignment = csp.backtrackingSearch();
		if (assignment == null) {
			System.out.println("Not able to be solved");
		} else {
			printAssignment(assignment);
		}

	}

	public static void printAssignment(HashMap<Variable, Value> assignment) {
		for (Variable v : assignment.keySet()) {
			System.out.print(v);
			System.out.println(" " + assignment.get(v));

		}

	}

	public static void printStringSet(ArrayList<Variable> assignment) {
		for (Variable v : assignment) {
			System.out.print(v + ", ");
		}
		System.out.println("");

	}

	public static void printValueSet(ArrayList<Value> assignment) {
		for (Value v : assignment) {
			System.out.print(v + ", ");
		}
		System.out.println("");
	}

	public static void printConstraintMap(HashMap<Variable, ArrayList<Constraint>> assignment) {
		for (Variable v : assignment.keySet()) {
			System.out.print(v);
			for (Constraint c : assignment.get(v)) {
				System.out.println("--> " + c);
			}

		}

	}

	public static void readfile(CSP csp) {

		String var;

		ArrayList<String> adjArr = new ArrayList<>();
		ArrayList<String> timeArr = new ArrayList<>();
		ArrayList<String> domainArry = new ArrayList<>();
		ArrayList<String> valueArr = new ArrayList<>();

		ArrayList<Variable> varArr = new ArrayList<>();

		ArrayList<Variable> variables = new ArrayList<>();
		ArrayList<Value> domain = new ArrayList<>();

		HashMap<Variable, ArrayList<Constraint>> constraintMap = new HashMap<Variable, ArrayList<Constraint>>();

		Variable convariable;

		int size = 0;
		System.out.print("Input the File name: ");
		System.out.print("");
		try {
			Scanner scnr = new Scanner(System.in);
			String filename = scnr.next();
			File inputfile = new File(filename);
			scnr.close();

			Scanner fileScnr = new Scanner(inputfile);
			String line1 = fileScnr.nextLine();

			if (line1.equals("map")) {
				fileScnr.nextLine();

				var = fileScnr.nextLine();
				ArrayList<String> stringVars = new ArrayList<>(Arrays.asList(var.split(" ")));

				for (int i = 0; i < stringVars.size(); i++) {
					Variable v = new Variable(stringVars.get(i), 0);
					variables.add(v);
				}

				size = variables.size();

				while (fileScnr.hasNextLine()) {
					String data = fileScnr.nextLine();
					for (int i = 0; i < size; i++) {
						adjArr = new ArrayList<>(Arrays.asList(data.split(" ")));
						String adj = adjArr.get(0);
						convariable = new Variable(adj, 0);
						constraintMap.put(convariable, new ArrayList<Constraint>());

						for (int j = 1; j < adjArr.size(); j++) {
							Variable adjVar = new Variable((adjArr.get(j)), 0);
							// AjacencyConstraint adjCon = new AjacencyConstraint(adjArr.get(0),
							// (adjArr.get(j)));
							AjacencyConstraint adjCon = new AjacencyConstraint(convariable, adjVar);
							constraintMap.get(convariable).add(adjCon);

						}

						data = fileScnr.nextLine();

					}
					domainArry = new ArrayList<>(Arrays.asList(data.split(" ")));
					for (int i = 0; i < domainArry.size(); i++) {
						Value d = new Value(0, domainArry.get(i));
						domain.add(d);

					}

					while (fileScnr.hasNextLine()) {// String
						data = fileScnr.nextLine();
						valueArr = new ArrayList<>(Arrays.asList(data.split(" ")));

						if (valueArr.contains("=")) {
							Variable region = new Variable(valueArr.get(0), 0);
							Value color = new Value(0, valueArr.get(2));
							ColorConstraint colorCon = new ColorConstraint(region, color, true);
							// Variable.getVariable(region, constraintMap);

							ArrayList<Constraint> val = constraintMap.get(region);

							constraintMap.get(region).add(colorCon);

						}
						if (valueArr.contains("!=")) {
							Variable region = new Variable(valueArr.get(0), 0);
							Value color = new Value(0, valueArr.get(2));

							ColorConstraint colorCon = new ColorConstraint(region, color, false);
							constraintMap.get(region).add(colorCon);

						}

					}

				}
				csp.setConstraintMap(constraintMap);

				csp.setDomain(domain);
				csp.setVariables(variables);

			}
			if (line1.equals("jobshop")) {
				fileScnr.nextLine();

				var = fileScnr.nextLine();
				ArrayList<String> stringVars = new ArrayList<>(Arrays.asList(var.split(" ")));

				String data = fileScnr.nextLine();
				size = stringVars.size();
				Variable v;
				ArrayList<ArrayList> timeset = new ArrayList<>();

				for (int i = 0; i < size; i++) {
					timeArr = new ArrayList<>(Arrays.asList(data.split(" ")));
					timeset.add(timeArr);

					data = fileScnr.nextLine();

				}

				Iterator<ArrayList> it = timeset.iterator();
				Map<String, Integer> timemap = new HashMap<String, Integer>();

				for (ArrayList list : timeset) {
					int time = Integer.parseInt((String) list.get(1));
					String key = list.get(0).toString();
					timemap.put(key, time);
					v = new Variable(key, time);
					variables.add(v);
					constraintMap.put(v, new ArrayList<Constraint>());

				}

				int Tmax = Integer.parseInt(data.toString());
				for (int i = 0; i <= Tmax; i++) {
					Value d = new Value(0, Integer.toString(i));
					domain.add(d);

				}

				while (fileScnr.hasNextLine()) {// String
					data = fileScnr.nextLine();
					valueArr = new ArrayList<>(Arrays.asList(data.split(" ")));

					Variable T1;
					Variable T2;
					if (valueArr.contains("before")) {
						// String v = Variable.getName(valueArr.get(0));
						String var1 = valueArr.get(0);
						String var2 = valueArr.get(2);

						int t1 = timemap.get(var1);
						int t2 = timemap.get(var2);

						T1 = new Variable(var1, t1);
						T2 = new Variable(var2, t2);
						BeforeConstraint beforeCon = new BeforeConstraint(T1, T2);
						BeforeConstraint beforeCon2 = new BeforeConstraint(T1, T2);
						constraintMap.get(T1).add(beforeCon);
						// constraintMap.get(T2).add(beforeCon2);

					}
					if (valueArr.contains("disjoint")) {
						String var1 = valueArr.get(0);
						String var2 = valueArr.get(2);
						int t1 = timemap.get(var1);
						int t2 = timemap.get(var2);

						T1 = new Variable(var1, t1);
						T2 = new Variable(var2, t2);
						DisjointConstraint disCon = new DisjointConstraint(T1, T2);
						DisjointConstraint disCon2 = new DisjointConstraint(T1, T2);
						constraintMap.get(T1).add(disCon);
						constraintMap.get(T2).add(disCon2);

					}

					// }

				}
				csp.setConstraintMap(constraintMap);

				csp.setDomain(domain);

				csp.setVariables(variables);

			}
			fileScnr.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");

		}

	}

}