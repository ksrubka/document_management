package pl.com.bottega.wzorce.state.mars_rover.moving_strategies;

import pl.com.bottega.wzorce.state.mars_rover.Position;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public interface MovingStrategy {

    Position move(Position currentPosition);
}
