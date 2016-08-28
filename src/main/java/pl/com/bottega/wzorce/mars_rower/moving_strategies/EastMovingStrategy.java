package pl.com.bottega.wzorce.mars_rower.moving_strategies;

import pl.com.bottega.wzorce.mars_rower.Position;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class EastMovingStrategy implements MovingStrategy {

    @Override
    public Position move(Position currentPosition) {
        return new Position(currentPosition.x(), currentPosition.y() + 1);
    }
}