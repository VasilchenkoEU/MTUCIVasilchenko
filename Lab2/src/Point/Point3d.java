package Point;
/*
Класс для представления точек в трехмерном
Евклидовом пространстве
 */
public class Point3d {
    /*Координата x*/
    private double xCoord;
    /*Координата y*/
    private double yCoord;
    /*Координата z*/
    private double zCoord;
    /*Конструктор*/
    public Point3d(double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    /*Конструктор по умолчанию с координатами
    * x = 0.0
    * y = 0.0
    * z = 0.0
    * */
    public Point3d(){
        this(0.0,0.0,0.0);
    }
    /*Возвращение координаты x*/
    public double getX(){
        return xCoord;
    }
    /*Возвращение координаты y*/
    public double getY(){
        return yCoord;
    }
    /*Возвращение координаты z*/
    public double getZ(){
        return zCoord;
    }
    /*Установка нового значения x*/
    public void setX(double val){
        xCoord = val;
    }
    /*Установка нового значения y*/
    public void setY(double val){
        yCoord = val;
    }
    /*Установка нового значения z*/
    public void setZ(double val){
        zCoord = val;
    }

    /*Метод для сравнения значений двух объектов Point3d*/
    public static boolean equalsValuePoint3d(Point3d value1, Point3d value2){
        if (value1.getX() == value2.getX() && value1.getY() == value2.getY() && value1.getZ() == value2.getZ()){
            return false;
        } else {
            return true;
        }
    }

    /*Метод для вычисления расстояния между двумя точками*/
    public static double distanceTo(Point3d value1, Point3d value2){
        /*
        * формула нахождения - sqrt((x1-x2)^2+(y1-y2)^2+(z1-z2)^2)
        * */
        /*Задаём переменные*/
        double differenceX; // разница между координатами x1 и x2
        double differenceY; // разница между координатами y1 и y2
        double differenceZ; // разница между координатами z1 и z2
        double sum; // сумма
        double finalResult = 0; // полученная разница
        /*Находим скобки ((x1-x2)^2+(y1-y2)^2+(z1-z2)^2)*/
        differenceX = Math.pow(value1.getX() - value2.getX(), 2);
        differenceY = Math.pow(value1.getY() - value2.getY(), 2);
        differenceZ = Math.pow(value1.getZ() - value2.getZ(), 2);
        sum = differenceX + differenceY + differenceZ;
        /*Находим корень*/
        finalResult = Math.sqrt(sum);
        /*Форматирование - 2 знака после запятой*/

        /* TODO
        * В условии задачи сказано, что нужно вывести значение
        * с ТОЧНОСТЬЮ до двух знаков.
        * В данном случае используем floor (округление в меньшую сторону)
        * что бы сохранить второй знак после запятой без округления.
        * Например, если использовать round, то из значения 5.9160797831
        * мы получим 5.92
        * */
        finalResult = Math.floor(finalResult * 100.0) / 100.0;
        return finalResult;
    }
}