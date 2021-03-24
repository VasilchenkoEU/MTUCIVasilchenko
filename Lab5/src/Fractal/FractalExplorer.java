package Fractal;

import Display.JImageDisplay;

import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.awt.image.*;

/**
 Этот класс позволяет исследовать различные части фрактала с помощью
 создание и отображение графического интерфейса Swing
 */
public class FractalExplorer
{
    /*Целочисленный размер дисплея*/
    private int displaySize;

    /*
     JImageDisplay
     */
    private JImageDisplay display;

    /*FractalGenerator*/
    private FractalGenerator fractal;

    /**
     * A Rectangle2D.Double
     */
    private Rectangle2D.Double range;

    /*
    Конструктор с инициализацией
     */
    public FractalExplorer(int size) {
        /*Сохраняем размер*/
        displaySize = size;

        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);

    }

    /**
     Этот метод инициализирует графический интерфейс Swing с помощью JFrame,
     содержащего объект отображения изображения
     кнопку для сброса дисплея,
     кнопку для сохранения текущего фрактального изображения
     JComboBox для выбора типа фрактала.
     JComboBox хранится в JPanel
     */
    public void createAndShowGUI()
    {
        display.setLayout(new BorderLayout());
        JFrame myFrame = new JFrame("Фрактал");

        myFrame.add(display, BorderLayout.CENTER);

        /*Кнопка сбросить*/
        JButton resetButton = new JButton("Сбросить");

        ButtonHandler resetHandler = new ButtonHandler();
        resetButton.addActionListener(resetHandler);

        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);

        /*Закрытие окна*/
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /*Установка ComboBox*/
        JComboBox myComboBox = new JComboBox();

        /*Добавляем каждый фрактал в комбобокс*/
        FractalGenerator mandelbrotFractal = new Mandelbrot();
        myComboBox.addItem(mandelbrotFractal);
        FractalGenerator tricornFractal = new Tricorn();
        myComboBox.addItem(tricornFractal);
        FractalGenerator burningShipFractal = new BurningShip();
        myComboBox.addItem(burningShipFractal);

        /*Обработчик*/
        ButtonHandler fractalChooser = new ButtonHandler();
        myComboBox.addActionListener(fractalChooser);

        /**
         Создание JPanel
         */
        JPanel myPanel = new JPanel();
        JLabel myLabel = new JLabel("Фрактал:");
        myPanel.add(myLabel);
        myPanel.add(myComboBox);
        myFrame.add(myPanel, BorderLayout.NORTH);

        /**
         Кнопка сохранения
         */
        JButton saveButton = new JButton("Сохранить");
        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myFrame.add(myBottomPanel, BorderLayout.SOUTH);

        ButtonHandler saveHandler = new ButtonHandler();
        saveButton.addActionListener(saveHandler);

        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);

    }

    /*
     Отображение фрактала
     */
    private void drawFractal()
    {
        /*Цикл отображения*/
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){

                /*
                 Найти соответствующие координаты xCoord и yCoord
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

                /*Если число итераций равно -1, установите пиксель черным*/
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }

                else {
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);

                    display.drawPixel(x, y, rgbColor);
                }

            }
        }
        display.repaint();
    }
    /*
     Внутренний класс для обработки событий ActionListener
     */
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();

            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                fractal = (FractalGenerator) mySource.getSelectedItem();
                fractal.getInitialRange(range);
                drawFractal();

            }
            else if (command.equals("Сбросить")) {
                fractal.getInitialRange(range);
                drawFractal();
            }
            else if (command.equals("Сохранить")) {

                JFileChooser myFileChooser = new JFileChooser();

                FileFilter extensionFilter =
                        new FileNameExtensionFilter("PNG Images", "png");
                myFileChooser.setFileFilter(extensionFilter);
                myFileChooser.setAcceptAllFileFilterUsed(false);
                int userSelection = myFileChooser.showSaveDialog(display);
                if (userSelection == JFileChooser.APPROVE_OPTION) {

                    java.io.File file = myFileChooser.getSelectedFile();
                    String file_name = file.toString();

                    try {
                        BufferedImage displayImage = display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(display,
                                exception.getMessage(), "Ошибка сохранения!",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
                else return;
            }
        }
    }

    /*
     Внутренний класс для обработки событий MouseListener с дисплея
     */
    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            /*Получить координату х области отображения щелчка мыши*/
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x,
                    range.x + range.width, displaySize, x);

            /*Получить координату y области отображения щелчка мыши*/
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y,
                    range.y + range.height, displaySize, y);

            /**
             Вызовите метода recenter И Zoom Range()
             */
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            /*
             Перерисовка фрактала после изменений
             */
            drawFractal();
        }
    }

    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(600);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}