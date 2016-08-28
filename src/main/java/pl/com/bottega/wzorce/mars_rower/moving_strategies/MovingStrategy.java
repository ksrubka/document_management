package pl.com.bottega.wzorce.mars_rower.moving_strategies;

import pl.com.bottega.wzorce.mars_rower.Position;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public interface MovingStrategy {

    Position move(Position currentPosition);
}
