package ch.heigvd.mcr.entities;

import java.util.List;

public record Position(int x, int y) {
    public static final Position UP = new Position(0, -1);
    public static final Position DOWN = new Position(0, 1);
    public static final Position LEFT = new Position(-1, 0);
    public static final Position RIGHT = new Position(1, 0);
    public static final Position UP_LEFT = new Position(-1, -1);
    public static final Position UP_RIGHT = new Position(1, -1);
    public static final Position DOWN_LEFT = new Position(-1, 1);
    public static final Position DOWN_RIGHT = new Position(1, 1);

    public static final List<Position> DIRECTIONS =
            List.of(UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT);

    public static Position randomDirection() {
        return DIRECTIONS.get((int) (Math.random() * DIRECTIONS.size()));
    }

    public static Position add(Position p1, Position p2) {
        return new Position(p1.x + p2.x, p1.y + p2.y);
    }
}
