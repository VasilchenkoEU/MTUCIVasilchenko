package Display;

import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

/*
 Этот класс позволяет нам отображать наши фракталы.
 Он происходит от javax.swing.JКомпонент.
 */
public class JImageDisplay extends JComponent
{
    /*
     Экземпляр BufferedImage - управляет изображением,
     содержимое которого мы можем записать.
     */
    private BufferedImage displayImage;

    /*
     Конструктор принимает целочисленные значения ширины и высоты
     и инициализирует его объект
     */
    public JImageDisplay(int width, int height) {
        displayImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Dimension imageDimension = new Dimension(width, height);
        super.setPreferredSize(imageDimension);
    }
    /*
     Реализация суперкласса paintComponent
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(),
                displayImage.getHeight(), null);
    }
    /*
    Устанавливает все пиксели в данных изображениях в черный цвет.
     */
    public void clearImage()
    {
        int[] blankArray = new int[getWidth() * getHeight()];
        displayImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
    }
    /*
     Устанавливает пиксель в определенный цвет.
     */
    public void drawPixel(int x, int y, int rgbColor)
    {
        displayImage.setRGB(x, y, rgbColor);
    }

    public BufferedImage getImage() {
        return null;
    }
}