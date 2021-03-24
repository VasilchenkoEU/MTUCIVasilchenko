package Fractal;

import java.awt.geom.Rectangle2D;

public class BurningShip extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2.5;
        range.width = 4;
        range.height = 4;
    }

    public int numIterations(double x, double y)
    {
        int iteration = 0;
        double real = 0;
        double imaginary = 0;

        while (iteration < MAX_ITERATIONS &&
                real * real + imaginary * imaginary < 4)
        {
            double zrealUpdated = real * real - imaginary * imaginary + x;
            double zimaginaryUpdated = 2 * Math.abs(real)
                    * Math.abs(imaginary) + y;

            real = zrealUpdated;
            imaginary = zimaginaryUpdated;

            iteration = iteration + 1;
        }
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }
        return iteration;
    }

    public String toString() {
        return "Burning Ship";
    }
}