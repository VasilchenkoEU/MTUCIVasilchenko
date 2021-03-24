package Fractal;

import java.awt.geom.Rectangle2D;

/*
Для вычисления Фрактал Мандельброта
 */
public class Mandelbrot extends FractalGenerator
{
    /*
     * Константа - максимальное количество итераций
     */
    public static final int MAX_ITERATIONS = 2000;
    /*
     * Этот метод позволяет фрактальному генератору указать, какая часть
     * комплексной плоскости является наиболее интересной для фрактала.
     * Ему передается объект rectangle, и метод изменяет поля
     * rectangle, чтобы показать правильный начальный диапазон для фрактала.
     * Эта реализация устанавливает начальный диапазон в
     * (-2 - 1.5 i) - (1 + 1.5 i) или x=-2, y=-1,5, ширина=высота=3.
     */
    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -1.5;
        range.width = 3;
        range.height = 3;
    }
    /*
     * Этот метод реализует итерационную функцию для фрактала Мандельброта.
     */
    public int numIterations(double x, double y)
    {
        /*Начало с 0*/
        int iteration = 0;
        /*Инициализация переменных*/
        double real = 0;
        double imaginary = 0;
        /**
         Вычислить Zn = Zn-1^2 + c, где значения представляют собой
         комплексные числа.
         */
        while (iteration < MAX_ITERATIONS &&
                real * real + imaginary * imaginary < 4)
        {
            double zrealUpdated = real * real - imaginary * imaginary + x;
            double zimaginaryUpdated = 2 * real * imaginary + y;
            real = zrealUpdated;
            imaginary = zimaginaryUpdated;
            iteration = iteration + 1;
        }
        /*
         Если достигнуто максимальное число итераций, верните значение -1
         -1 указывает, на то, что точка не вышла за пределы границы.
         */
        if (iteration == MAX_ITERATIONS)
        {
            return -1;
        }
        return iteration;
    }
    public String toString() {
        return "Mandelbrot";
    }
}