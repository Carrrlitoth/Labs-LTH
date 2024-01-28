package mountain;

import java.util.HashMap;

import fractal.*;

public class Mountain extends Fractal {
    private Point a;
    private Point b;
    private Point c;
    private double dev;
    private HashMap<Side, Point> map;

    public Mountain (Point a, Point b, Point c, double dev) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.dev = dev;
        map = new HashMap<Side, Point>();
    }

    @Override
    public String getTitle() {
        return "Mountain triangel";
    }

    public Point mid(Point a, Point b, double dev) {
        double rand = RandomUtilities.randFunc(dev);
        Side s = new Side(a, b);
        if(map.containsKey(s) ) {
            Point temp = map.get(s);
            map.remove(s);
            return temp;
        }

        Point midPoint = new Point((a.getX()+b.getX())/2, (a.getY()+b.getY())/2+(int)rand);
        map.put(s, midPoint);
        return midPoint;
    }

    @Override
    public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c, dev);
	}

    private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
		    turtle.forwardTo(b.getX(), b.getY());
		    turtle.forwardTo(c.getX(), c.getY());
		    turtle.forwardTo(a.getX(), a.getY());
			} else {
                Point mid1 = mid(a, b, dev);
                Point mid2 = mid(b, c, dev);
                Point mid3 = mid(c, a, dev);

                dev = dev/2;

                fractalLine(turtle, order - 1, a, mid1, mid3, dev);
                fractalLine(turtle, order - 1, b, mid1, mid2, dev);
                fractalLine(turtle, order - 1, c, mid2, mid3, dev);
                fractalLine(turtle, order - 1, mid1, mid2, mid3, dev);
			}
			
	}
    
}
