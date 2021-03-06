package pl.com.bottega.wzorce.state.mars_rover;

import pl.com.bottega.wzorce.state.mars_rover.states.MarsRoverState;
import pl.com.bottega.wzorce.state.mars_rover.states.NorthState;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class MarsRover {

    private Position currentPosition;
    private MarsRoverState currentState;

    public MarsRover() {
        this.currentPosition = new Position(0, 0);
        this.currentState = new NorthState(this);
    }

    public void move() {
        currentState.move();
    }

    public void rotateLeft() {
        currentState.rotateLeft();
    }

    public void rotateRight() {
        currentState.rotateRight();
    }

    public Position position() {
        return currentPosition;
    }

    public void setPosition(Position position) {
        currentPosition = position;
    }

    public void setState(MarsRoverState state) {
        this.currentState = state;
    }
}
