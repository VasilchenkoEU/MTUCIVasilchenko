package RunApplication;

import Point.Point2d;
import Point.Point3d;

import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args){
        /*Создаём объект класса Point 3d*/
        Scanner scanner = new Scanner(System.in); // Для ввода с клавиатуры
        /*Переменные для создания точек*/
        double x;
        double y;
        double z;
        System.out.println("Вводите только числа в формате n или n,n (с плавающей запятой)");
        /*Ввод данных для Точки 1*/
        System.out.println("Введите значение x для точки 1:");
        x = scanner.nextDouble();
        System.out.println("Введите значение y для точки 1:");
        y = scanner.nextDouble();
        System.out.println("Введите значение z для точки 1:");
        z = scanner.nextDouble();
        /*Создание объекта типа Point3d*/
        Point3d point3dOne = new Point3d(x, y, z);

        /*Ввод данных для Точки 2*/
        System.out.println("Введите значение x для точки 2:");
        x = scanner.nextDouble();
        System.out.println("Введите значение y для точки 2:");
        y = scanner.nextDouble();
        System.out.println("Введите значение z для точки 2:");
        z = scanner.nextDouble();
        /*Создание объекта типа Point3d*/
        Point3d point3dTwo = new Point3d(x, y, z);

        /*Ввод данных для Точки 3*/
        System.out.println("Введите значение x для точки 3:");
        x = scanner.nextDouble();
        System.out.println("Введите значение y для точки 3:");
        y = scanner.nextDouble();
        System.out.println("Введите значение z для точки 3:");
        z = scanner.nextDouble();
        /*Создание объекта типа Point3d*/
        Point3d point3dThree = new Point3d(x, y, z);

        /*Проверим координаты 3х точек*/
        if (Point3d.equalsValuePoint3d(point3dOne, point3dTwo)){
            if (Point3d.equalsValuePoint3d(point3dOne, point3dThree)){
                if (Point3d.equalsValuePoint3d(point3dTwo, point3dThree)){
                    /*Запускаем метод для нахождения площади*/
                    System.out.println("S (площадь треугольника) = " + computeArea(point3dOne, point3dTwo, point3dThree));
                } else {
                    System.out.println("Координаты point3dTwo и point3dThree одинаковые!\n" +
                            "Пожалуйста, измените данные.");
                }
            } else {
                System.out.println("Координаты point3dOne и point3dThree одинаковые!\n" +
                        "Пожалуйста, измените данные.");
            }
        } else {
            System.out.println("Координаты point3dOne и point3dTwo одинаковые!\n" +
                    "Пожалуйста, измените данные.");
        }
    }

    /*Метод для нахождения площади треугольника - формула Герона*/
    public static double computeArea(Point3d x, Point3d y, Point3d z){
        /*Переменные для нахождения площади*/
        double side1; // Сторона от точки x до точки y
        double side2; // Сторона от точки y до точки z
        double side3; // Сторона от точки z до точки x
        double p; // Полупериметр треугольника
        double S; // Площадь треугольника

        /*Расстояние от точки x до точки y*/
        side1 = Point3d.distanceTo(x, y);
        System.out.println("Расстояние от точки x до точки y = " + side1);
        /*Расстояние от точки y до точки z*/
        side2 = Point3d.distanceTo(y, z);
        System.out.println("Расстояние от точки y до точки z = " + side2);
        /*Расстояние от точки z до точки x*/
        side3 = Point3d.distanceTo(z, x);
        System.out.println("Расстояние от точки z до точки x = " + side3);

        /*Находим полупериметр треугольника
        * формула: P = 1/2(a+b+c)
        * */
        p = (side1 + side2 + side3)/2;

        /* Находим площадь треугольника
        * формула: S = sqrt(p(p-a)(p-b)(p-c))
        * */
        S = Math.sqrt(p*(p-side1)*(p-side2)*(p-side3));
        return S;
    }
}