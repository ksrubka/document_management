package pl.com.bottega.wzorce.mars_rower;

import pl.com.bottega.wzorce.mars_rower.moving_strategies.*;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class MovingStrategyFactory {

    public static MovingStrategy create(Direction direction) {
        switch (direction) {
            case N:
                return new NorthMovingStrategy();
            case NW:
                return new NWMovingStrategy();
            case W:
                return new WestMovingStrategy();
            case SW:
                return new SWMovingStrategy();
            case S:
                return new SouthMovingStrategy();
            case SE:
                return new SEMovingStrategy();
            case E:
                return new EastMovingStrategy();
            case NE:
                return new NEMovingStrategy();
            default:
                System.out.println("Something went wrong rotating left");
        }
        return null;
    }
}
