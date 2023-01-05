
public class Value {
	String s;
	int val;

	Value(int i, String s) {
		this.val = i;
		this.s = s;

	}

	public Value(int i) {
		this.val = i;
	}

	@Override
	public String toString() {
		return s;
	}
	// abstract int getInt();

	public String getInt() {
		return s;
	}

}
