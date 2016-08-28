package pl.com.bottega.wzorce.state.mars_rover.moving_strategies;

import pl.com.bottega.wzorce.state.mars_rover.Position;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class SouthMovingStrategy implements MovingStrategy {

    @Override
    public Position move(Position currentPosition) {
        return new Position(currentPosition.x() - 1, currentPosition.y());
    }
}