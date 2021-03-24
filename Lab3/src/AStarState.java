import java.util.*;

/*
    Этот класс хранит базовое состояние, необходимое для алгоритма A * для вычисления
    путей по карте. Это состояние включает в себя набор открытых путевых точек.
    и еще один набор закрытых путевых точек. Кроме того, этот класс
    предоставляет основные операции, необходимые алгоритму поиска пути A * для его обработки.
*/

public class AStarState
{
    // ссылка на карту
    private Map2D map;

    /*
    Инициализация карты со всеми открытыми точками
    */
    private Map<Location, Waypoint> openPoint = new HashMap<> ();

    /*
    Инициализация карты со всеми закрытыми точками
    */
    private Map<Location, Waypoint> closedPoint = new HashMap<> ();

    /*
     Инициализация нового объекта для использования алгоритма A
    */
    public AStarState(Map2D map)
    {
        if (map == null)
            // в случаее map = null бросаем исключение (что бы программа аварийно не закончила работу)
            throw new NullPointerException("Map не должен быть пустым");
        this.map = map;
    }

    /*
    Возращаем карту
    */
    public Map2D getMap()
    {
        return map;
    }

    /*
     Этот метод просматривает все открытые путевые точки и возвращает путевую точку.
     с минимальной общей стоимостью.
     */
    public Waypoint getMinOpenWaypoint()
    {
        // Если нет точек для прохождения, вовращаем null.
        if (numOpenWaypoints() == 0)
            return null;

        // Перебираем и сохраняем все открытые точки
        Set open_waypoint_keys = openPoint.keySet();
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;

        // Цикл по перебору точек
        while (i.hasNext())
        {
            // Сохраняем локацию
            Location location = (Location)i.next();
            // Сохраняем точку
            Waypoint waypoint = openPoint.get(location);
            // Сохраняем общую стоимость полученной точки
            float waypoint_total_cost = waypoint.getTotalCost();

            /*
            Если общая стоимость текущей путевой точки меньше
            чем сохраненная стоимость, то заменим
            сохраненную путевую точку на текущую путевую точку
            и сохраненную общую стоимость на текущую общую стоимость.
            */
            if (waypoint_total_cost < best_cost)
            {
                best = openPoint.get(location);
                best_cost = waypoint_total_cost;
            }

        }
        // Вернём точку
        return best;
    }

    /*
     Этот метод добавляет путевую точку в коллекцию "открытых путевых точек"
     Если уже есть путевая точка в местоположении новой путевой точки, новая путевая точка заменяет
     старую только если значение "предыдущей стоимости" новой путевой точки
     меньше, чем значение "предыдущей стоимости" текущей путевой точки.
     */
    public boolean addOpenWaypoint(Waypoint waypoint)
    {
        // Поиск местонахождения новой точки
        Location location = waypoint.getLocation();

        /*
        Условие ниже проверяет, есть ли уже открытая путевая точка в новом
        местоположение путевой точки.
        */
        if (openPoint.containsKey(location))
        {
            /*
            Если в новой путевой точке уже есть открытая путевая точка
            то проверяем, является ли новая путевая точка "предыдущей":
            значение "стоимости" меньше, чем "предыдущее" текущей путевой точки
            */
            Waypoint current_waypoint = openPoint.get(location);
            if (waypoint.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                /*
                Если значение "предыдущей стоимости" новой путевой точки меньше, чем
                значение "предыдущей стоимости" текущей путевой точки, то новая путевая точка
                заменяет старую путевую точку и возвращает true.
                */
                openPoint.put(location, waypoint);
                return true;
            }
            /*
            Если значение "предыдущей стоимости" новой путевой точки не меньше,
            чем значение "предыдущей стоимости" текущей путевой точки, возвращаем false.
             */
            return false;
        }

        // Если в новой путевой точке еще нет открытой путевой точки
        // то добавляем новую путевую точку в коллекцию открытых путевых точек
        openPoint.put(location, waypoint);
        return true;
    }


    /*
    Возвращает количество открытых точек
    */
    public int numOpenWaypoints()
    {
        return openPoint.size();
    }

    /*
     Этот метод перемещает путевую точку в указанное место из
     открытого списка к закрытому.
     **/
    public void closeWaypoint(Location location)
    {
        Waypoint waypoint = openPoint.remove(location);
        closedPoint.put(location, waypoint);
    }

    /*
    Возврат истины, если в закрытых точках находится путевая точка
     */
    public boolean isLocationClosed(Location location)
    {
        return closedPoint.containsKey(location);
    }
}