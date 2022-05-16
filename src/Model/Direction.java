package Model;

public enum Direction {
    NORTH("North"), WEST("West"), EAST("East"), SOUTH("South");

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }
}
