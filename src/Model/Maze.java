package Model;

public class Maze {
    private Room[][] maze;
    private int characterRow;
    private int characterColumn;

    Maze(int chosenRows, int chosenColumns) {
        characterRow = 0;
        characterColumn = 0;

        for (int i = 0; i < chosenRows; i++) {
            for (int j = 0; j < chosenColumns; j++) {
                maze[i][j] = new Room();
            }
        }

        lockEdgeDoors();
    }

    private void move(Direction directionToMove) {
        Door chosenDoor = maze[characterRow][characterColumn].getDoor(directionToMove);
        chosenDoor.getQuestion().askQuestion();
        if (!chosenDoor.isLocked()) {
            if (directionToMove == Direction.NORTH) {
                characterRow -= 1;
            } else if (directionToMove == Direction.EAST) {
                characterColumn += 1;
            } else if (directionToMove == Direction.SOUTH) {
                characterRow += 1;
            } else {
                characterColumn -= 1;
            }
        }
    }

    private boolean noValidPaths() {
        return false;
    }

    private void lockEdgeDoors() {
        for (int i = 0; i < maze.length; i++) {
            maze[i][0].getDoor(Direction.WEST).lockDoor();
            maze[i][maze[i].length - 1].getDoor(Direction.EAST).lockDoor();
            maze[0][i].getDoor(Direction.NORTH).lockDoor();
            maze[maze.length - 1][i].getDoor(Direction.SOUTH).lockDoor();
        }
    }
}
