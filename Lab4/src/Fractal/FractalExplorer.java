package Fractal;

import Display.JImageDisplay;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

/*
 Этот класс позволяет исследовать различные части фрактала с помощью
 создание и отображение графического интерфейса Swing
 */
public class FractalExplorer
{
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;

    /*Конструктор*/
    public FractalExplorer(int size) {
        displaySize = size; // задаем размер

        // Инициализирует фрактал-генератор и объекты диапазона
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
    }
    /*
    Рисуем GUI - графический интерфейс
     */
    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        JFrame myframe = new JFrame("Fractal Explorer");

        myframe.add(display, BorderLayout.CENTER);

        /*Кнопка показать изображение*/
        JButton resetButton = new JButton("Сбросить изображение");

        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);

        myframe.add(resetButton, BorderLayout.SOUTH);

        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }

    private void drawFractal()
    {
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){
                /* Найти соответствующие координаты xCoord и yCoord
                в области отображения фрактала.
                */
                double xCoord = fractal.getCoord(range.x,
                        range.x + range.width, displaySize, x);
                double yCoord = fractal.getCoord(range.y,
                        range.y + range.height, displaySize, y);

                /*
                 Вычислите количество итераций для координат в области
                 отображения фрактала.
                 */
                int iteration = fractal.numIterations(xCoord, yCoord);

                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }

                else {
                    /*
                     В противном случае выберите значение оттенка
                     на основе числа итераций.
                     */
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    /*Обновление цветов на дисплее*/
                    display.drawPixel(x, y, rgbColor);
                }

            }
        }
        /*
        Перекраска дисплея
        */
        display.repaint();
    }
    /*
    Внутренний класс для обработки событий ActionListener
    с помощью кнопки сброса.
    */
    private class ResetHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            fractal.getInitialRange(range);
            drawFractal();
        }
    }
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
                    range.x + range.width, displaySize, x);

            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
                    range.y + range.height, displaySize, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}