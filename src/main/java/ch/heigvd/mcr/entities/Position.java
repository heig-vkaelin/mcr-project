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
}
