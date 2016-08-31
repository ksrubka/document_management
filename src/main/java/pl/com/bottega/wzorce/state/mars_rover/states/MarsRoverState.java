package pl.com.bottega.wzorce.state.mars_rover.states;

import pl.com.bottega.wzorce.state.mars_rover.MarsRover;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public abstract class MarsRoverState {

    protected MarsRover marsRover;

    public MarsRoverState(MarsRover marsRover) {
        this.marsRover = marsRover;
    }

    public abstract void move();

    public abstract void rotateLeft();
    public abstract void rotateRight();


}
