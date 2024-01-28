package mountain;

public class Side {
    private Point a, b;
    public Side(Point a, Point b){
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    @Override
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Side other = (Side) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		}
		if (b == null) {
			if (other.b != null)
				return false;
		}
		if ((a.equals(other.a) || a.equals(other.b))
				&& (b.equals(other.b) || b.equals(other.a))) {
			return true;
		}

		return false;
	}
    @Override
    public int hashCode() {
        return a.hashCode() + b.hashCode();
        }
    
}
