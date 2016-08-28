package pl.com.bottega.wzorce.mars_rower;

import pl.com.bottega.wzorce.mars_rower.moving_strategies.MovingStrategy;

import static pl.com.bottega.wzorce.mars_rower.Direction.*;

/**
 * Created by Beata Iłowiecka on 28.08.2016.
 */
public class MarsRover {

    private Position currentPosition;
    private Direction direction;
    private MovingStrategy movingStrategy;

    public MarsRover(Position currentPosition, Direction direction) {
        this.currentPosition = currentPosition;
        this.direction = direction;
        movingStrategy = MovingStrategyFactory.create(direction);
    }

    public void move() {
        currentPosition = movingStrategy.move(currentPosition);
        System.out.println(currentPosition);
    }

    public  void rotateLeft() {
        switch (direction) {
            case N:
                direction = NW;
                break;
            case NW:
                direction = W;
                break;
            case W:
                direction = SW;
                break;
            case SW:
                direction = S;
                break;
            case S:
                direction = SE;
                break;
            case SE:
                direction = E;
                break;
            case E:
                direction = NE;
                break;
            case NE:
                direction = N;
                break;
        }
        movingStrategy = MovingStrategyFactory.create(direction);
        System.out.println(currentPosition);
    }

    public  void rotateRight() {
        switch (direction) {
            case N:
                direction = NE;
                break;
            case NE:
                direction = E;
                break;
            case E:
                direction = SE;
                break;
            case SE:
                direction = S;
                break;
            case S:
                direction = SW;
                break;
            case SW:
                direction = W;
                break;
            case W:
                direction = NW;
                break;
            case NW:
                direction = N;
                break;
        }
        movingStrategy = MovingStrategyFactory.create(direction);
        System.out.println(currentPosition);
    }

    Position getPosition() {
        return currentPosition;
    }
}
