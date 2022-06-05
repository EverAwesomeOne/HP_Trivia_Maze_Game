package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * The Maze class represents the maze the player navigates to play the game.
 */
public class Maze implements Serializable {
    private final Room[][] myMaze;
    private int myCharacterRow;
    private int myCharacterColumn;
    private LinkedList<Integer>[] myRoomConnections;

    @Serial
    private static final long serialVersionUID = 109174852462682090L;

    /**
     * Constructor for the Maze class.
     * Initializes instance fields for the character row and column
     * as well as the undirected graph of room connections and 2d maze array.
     * @param theMazeLength The length of the square maze
     */
    public Maze(final int theMazeLength) {
        final int chosenRows = theMazeLength;
        final int chosenColumns = theMazeLength;
        myCharacterRow = 0;
        myCharacterColumn = 0;
        myMaze = new Room[chosenRows][chosenColumns];

        for (int i = 0; i < chosenRows; i++) {
            for (int j = 0; j < chosenColumns; j++) {
                myMaze[i][j] = new Room();
            }
        }

        lockEdgeDoors();
        connectSharedDoors();
        createInitialGraph();
    }

    /**
     * Updates the players row or column depending on which direction they moved.
     * @param theDirectionToMove the direction the player moved in
     */
    public void updatePosition(final Direction theDirectionToMove) {
        if (theDirectionToMove == Direction.NORTH) {
            myCharacterRow -= 2;
        } else if (theDirectionToMove == Direction.EAST) {
            myCharacterColumn += 2;
        } else if (theDirectionToMove == Direction.SOUTH) {
            myCharacterRow += 2;
        } else {
            myCharacterColumn -= 2;
        }
    }

    /**
     * Checks to see if the player has won the game or not.
     * @return whether the game has been won
     */
    public boolean hasWon() {
        return myCharacterRow == myMaze.length - 1 && myCharacterColumn == myMaze.length - 1;
    }

    /**
     * Checks to see if a path to the exit still exists.
     * @return whether the game can still be won
     */
    public boolean hasValidPaths() {
        // performs a BFS traversal from the current room
        int roomNumber = myCharacterRow * myMaze.length + myCharacterColumn;

        // Mark all the nodes as not visited
        final boolean[] visited = new boolean[myMaze.length * myMaze[0].length];

        // Create a queue for BFS through the remaining room connections
        final LinkedList<Integer> queue = new LinkedList<>();

        // Mark the current node as visited and enqueue it
        visited[roomNumber] = true;
        queue.add(roomNumber);

        while (queue.size() != 0) {
            // Dequeue a node from queue and check if it is the exit room
            roomNumber = queue.poll();

            if (roomNumber == myMaze.length * myMaze[0].length - 1) {
                return true;
            }

            // Get all adjacent nodes of the dequeued node
            // If an adjacent node has not been visited, then mark it visited and enqueue it
            for (int n : myRoomConnections[roomNumber]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }

        }
        return false;
    }

    /**
     * Removes an edge (room connection) from the undirected graph.
     * @param theDirectionToMove the direction the player failed to move in
     */
    public void removeEdgeFromGraph(final Direction theDirectionToMove) {
        final int firstNodeToRemoveFrom = myCharacterRow * myMaze.length + myCharacterColumn;
        int secondNodeToRemoveFrom = firstNodeToRemoveFrom;

        if (theDirectionToMove == Direction.NORTH) {
            secondNodeToRemoveFrom -= 2 * myMaze.length;
        } else if (theDirectionToMove == Direction.EAST) {
            secondNodeToRemoveFrom += 2;
        } else if (theDirectionToMove == Direction.SOUTH) {
            secondNodeToRemoveFrom += 2 * myMaze.length;
        } else {
            secondNodeToRemoveFrom -= 2;
        }

        myRoomConnections[firstNodeToRemoveFrom].remove((Integer) secondNodeToRemoveFrom);
        myRoomConnections[secondNodeToRemoveFrom].remove((Integer) firstNodeToRemoveFrom);
    }

    /**
     * Creates the initial undirected graph with all room connections.
     */
    private void createInitialGraph() {
        myRoomConnections = new LinkedList[myMaze.length * myMaze[0].length];
        for (int i = 0; i < myRoomConnections.length; i += 2) {

            int rowIndex = i / myMaze.length;
            int columnIndex = i % myMaze[0].length;

            if ((i - 1) % myMaze.length == 0) {
                i += 6;
                rowIndex++;
                columnIndex--;
            }

            if (myRoomConnections[i] == null) {
                myRoomConnections[i] = new LinkedList<>();
            }

            // we only want to add valid edges to our undirected graph
            if (!myMaze[rowIndex][columnIndex].getDoor(Direction.NORTH).isLocked()) {
                myRoomConnections[i].add(i - 2 * myMaze.length);
            }
            if (!myMaze[rowIndex][columnIndex].getDoor(Direction.EAST).isLocked()) {
                myRoomConnections[i].add(i + 2);
            }
            if (!myMaze[rowIndex][columnIndex].getDoor(Direction.SOUTH).isLocked()) {
                myRoomConnections[i].add(i + 2 * myMaze.length);
            }
            if (!myMaze[rowIndex][columnIndex].getDoor(Direction.WEST).isLocked()) {
                myRoomConnections[i].add(i - 2);
            }
        }
    }

    /**
     * Connects doors between adjacent rooms so that they are shared (same reference).
     */
    private void connectSharedDoors() {
        // east and west door of adjacent rooms
        for (int i = 0; i < myMaze.length; i += 2) {
            for (int j = 0; j < myMaze[i].length - 2; j += 2) {
                final Door rightRoomWestDoor = myMaze[i][j + 2].getDoor(Direction.WEST);
                myMaze[i][j].setSharedDoor(Direction.EAST, rightRoomWestDoor);
            }
        }

        // north and south doors of adjacent rooms
        for (int i = 0; i < myMaze.length - 2; i += 2) {
            for (int j = 0; j < myMaze[i].length; j += 2) {
                final Door bottomRoomNorthDoor = myMaze[i + 2][j].getDoor(Direction.NORTH);
                myMaze[i][j].setSharedDoor(Direction.SOUTH, bottomRoomNorthDoor);
            }
        }
    }

    /**
     * Locks doors that would lead outside the maze.
     */
    private void lockEdgeDoors() {
        for (int i = 0; i < myMaze.length; i += 2) {
            // we want to lock the doors on the edge of the maze
            // since there are no rooms beyond that
            myMaze[i][0].getDoor(Direction.WEST).lockDoor(); // left
            myMaze[i][myMaze[i].length - 1].getDoor(Direction.EAST).lockDoor(); // right
            myMaze[0][i].getDoor(Direction.NORTH).lockDoor(); // top
            myMaze[myMaze.length - 1][i].getDoor(Direction.SOUTH).lockDoor(); // bottom
        }
    }

    /**
     * Gets the current room the player is in.
     * @return the room the player is in
     */
    public Room getCurrentRoom() {
        return myMaze[myCharacterRow][myCharacterColumn];
    }

    /**
     * Gets a specific room.
     * @param theRow the row of the room wanted
     * @param theCol the column of the room wanted
     * @return the room at the specified row and column
     */
    public Room getSpecificRoom(final int theRow, final int theCol) {
        return myMaze[theRow][theCol];
    }

    /**
     * Gets the player's current row.
     * @return the player's current row
     */
    public int getCharacterRow() {
        return myCharacterRow;
    }

    /**
     * Gets the player's current column.
     * @return the player's current column
     */
    public int getCharacterColumn() {
        return myCharacterColumn;
    }
}
