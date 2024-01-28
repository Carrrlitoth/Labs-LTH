package fractal;

import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[0] = new Koch(300);
		fractals[1] = new Mountain(new Point(250, 150), new Point(150, 400), new Point(400, 420 ), 20);
	    new FractalView(fractals, "Fraktaler", 600, 600);
	}

}
