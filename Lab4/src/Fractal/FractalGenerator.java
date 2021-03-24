package Fractal;

import java.awt.geom.Rectangle2D;


/*
 Данный класс предоставляет собой общий интерфейс и методы для фрактала
 */
public abstract class FractalGenerator {
     // rangeMin - минимальное значение диапазона с плавающей запятой
     // rangeMax - максимальное значение диапазона с плавающей запятой
     // size - размер измерения, из которого исходит координата пикселя
     // coord - координата для вычисления значения двойной точности

    public static double getCoord(double rangeMin, double rangeMax,
                                  int size, int coord) {
        assert size > 0;
        assert coord >= 0 && coord < size;
        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /*
     Устанавливает указанный прямоугольник,
     содержащий начальный диапазон, подходящий для
     генерируемый фрактал.
     */
    public abstract void getInitialRange(Rectangle2D.Double range);

    /*
     Обновляет текущий диапазон для центрирования
     по заданным координатам, а также для увеличения или уменьшения
     масштаба с заданным коэффициентом масштабирования.
     */
    public void recenterAndZoomRange(Rectangle2D.Double range,
                                     double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;
        range.y = centerY - newHeight / 2;
        range.width = newWidth;
        range.height = newHeight;
    }
    /**
     Учитывая координаты в комплексной плоскости,
     вычисляет и возвращает число итераций перед фракталом
     функция экранирует ограничивающую область для этой точки.
     Указывается точка, которая не убегает до достижения предела итерации
     с результатом -1.
     */
    public abstract int numIterations(double x, double y);
}
