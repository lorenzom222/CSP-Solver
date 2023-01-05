public class Variable {
    protected String name;
    int time;

    public Variable(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public int toInt(String var) {
        return time;
    }

    public int getint(String var) {
        var classInstance = new Variable(name, time);
        int time = classInstance.time;
        return time;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() == getClass())
            return this.name.equals(((Variable) obj).name);
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}