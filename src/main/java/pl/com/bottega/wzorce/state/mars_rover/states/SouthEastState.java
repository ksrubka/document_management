package pl.com.bottega.wzorce.state.mars_rover.states;

import pl.com.bottega.wzorce.state.mars_rover.MarsRover;
import pl.com.bottega.wzorce.state.mars_rover.Position;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class SouthEastState extends MarsRoverState {

    public SouthEastState(MarsRover marsRover) {
        super(marsRover);
    }

    @Override
    public void move() {
        Position position = marsRover.position();
        marsRover.setPosition(new Position(position.x() - 1, position.y() + 1));
    }

    @Override
    public void rotateLeft() {
        marsRover.setState(new EastState(marsRover));
    }

    @Override
    public void rotateRight() {
        marsRover.setState(new SouthState(marsRover));
    }
}
