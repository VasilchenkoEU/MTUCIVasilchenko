package Fractal;

import java.awt.geom.Rectangle2D;

/*
Класс для вычисления Фрактала
 */
public class Tricorn extends FractalGenerator
{
    /*
    Константа размера интераций
     */
    public static final int MAX_ITERATIONS = 2000;

    /*
     Этот метод позволяет фрактальному генератору указать, какая часть
     комплексной плоскости является наиболее интересной для фрактала.
     */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2;
        range.width = 2;
        range.height = 2;
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
            double zimaginaryUpdated = -2 * real * imaginary + y;
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

    /*
     Реализация toString().
     Возвращается название фрактала
     */
    public String toString() {
        return "Tricorn";
    }
}