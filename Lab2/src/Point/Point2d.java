package Point;
/*
Двумерный класс точки
 */
public class Point2d {
    /*Координата x*/
    private double xCoord;
    /*Координата y*/
    private double yCoord;
    /*Конструктор инициализации*/
    public Point2d(double x, double y){
        xCoord = x;
        yCoord = y;
    }
    /*Конструктор по умолчания*/
    public Point2d(){
        this(0,0);
    }
    /*Возвращение координаты X*/
    public double getX(){
        return xCoord;
    }
    /*Возвращение координаты Y*/
    public double getY(){
        return yCoord;
    }
    /*Установка значения координаты X*/
    public void setX(double val){
        xCoord = val;
    }
    /*Установка значения координаты Y*/
    public void setY(double val){
        yCoord = val;
    }
}
