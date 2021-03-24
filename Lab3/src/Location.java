public class Location
{
    /*
    Переменная для хранения координаты x
    */
    public int xCoord;

    /*
    Переменная для хранения координаты y
    */
    public int yCoord;


    /*
    Конструктор класса
    */
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /*
    Конструктор класса по умолчанию (будут создаваться координаты 0, 0)
    */
    public Location()
    {
        this(0, 0);
    }

    /*
    Метод для сравнения координат
    */
    public boolean equals(Object obj) {

        // Проверка, что объект принадлежит классу Location
        if (obj instanceof Location) {

            // Приводим объект к типу Location
            Location other = (Location) obj;
            if (xCoord == other.xCoord && yCoord == other.yCoord) {
                return true;
            }
        }
        return false;
    }

    /*
    Хэширование для каждого местоположения
    */
    public int hashCode() {
        int result = 10; // полученынй результат
        result = result + xCoord;
        result = result + yCoord;
        return result;
    }
}