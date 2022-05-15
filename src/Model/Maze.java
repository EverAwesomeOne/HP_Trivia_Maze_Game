package Model;

import java.util.Iterator;
import java.util.LinkedList;

public class Maze {
    private final Room[][] maze;
    private int characterRow;
    private int characterColumn;
    private LinkedList<Integer>[] roomConnections;

    public Maze(int chosenRows, int chosenColumns) {
        characterRow = 0;
        characterColumn = 0;
        maze = new Room[chosenRows][chosenColumns];

        for (int i = 0; i < chosenRows; i++) {
            for (int j = 0; j < chosenColumns; j++) {
                maze[i][j] = new Room();
            }
        }

        lockEdgeDoors();
        connectSharedDoors();
        createInitialGraph();
    }

    public void updatePosition(Direction directionToMove) {
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

    // performs a BFS traversal from the current room
    boolean hasValidPaths() {
        int roomNumber = characterRow * maze.length + characterColumn;

        // Mark all the nodes as not visited
        boolean[] visited = new boolean[maze.length * maze[0].length];

        // Create a queue for BFS through the remaining room connections
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[roomNumber] = true;
        queue.add(roomNumber);

        while (queue.size() != 0) {
            // Dequeue a node from queue and check if it is the exit room
            roomNumber = queue.poll();

            if (roomNumber == maze.length * maze[0].length - 1) {
                return true;
            }

            // Get all adjacent nodes of the dequeued node
            // If an adjacent node has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = roomConnections[roomNumber].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }

        return false;
    }

    void removeEdgeFromGraph(int firstNodeToRemoveFrom, int secondNodeToRemoveFrom) {
        roomConnections[firstNodeToRemoveFrom].remove((Integer) secondNodeToRemoveFrom);
        roomConnections[secondNodeToRemoveFrom].remove((Integer) firstNodeToRemoveFrom);
    }

    private void createInitialGraph() {
        roomConnections = new LinkedList[maze.length * maze[0].length];
        for (int i = 0; i < roomConnections.length; i++) {
            int rowIndex = i / maze.length;
            int columnIndex = i % maze[0].length;

            if (roomConnections[i] == null) {
                roomConnections[i] = new LinkedList<Integer>();
            }

            // we only want to add valid edges to our undirected graph
            if (!maze[rowIndex][columnIndex].getDoor(Direction.NORTH).isLocked()) {
                roomConnections[i].add(i - maze.length);
            }
            if (!maze[rowIndex][columnIndex].getDoor(Direction.EAST).isLocked()) {
                roomConnections[i].add(i + 1);
            }
            if (!maze[rowIndex][columnIndex].getDoor(Direction.SOUTH).isLocked()) {
                roomConnections[i].add(i + maze.length);
            }
            if (!maze[rowIndex][columnIndex].getDoor(Direction.WEST).isLocked()) {
                roomConnections[i].add(i - 1);
            }
        }
    }

    private void connectSharedDoors() {
        // we want doors to adjacent rooms to be shared

        // east and west door of adjacent rooms
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length - 1; j++) {
                Door rightRoomWestDoor = maze[i][j + 1].getDoor(Direction.WEST);
                maze[i][j].setSharedDoor(Direction.EAST, rightRoomWestDoor);
            }
        }

        // north and south doors of adjacent rooms
        for (int i = 0; i < maze.length - 1; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                Door bottomRoomNorthDoor = maze[i + 1][j].getDoor(Direction.NORTH);
                maze[i][j].setSharedDoor(Direction.SOUTH, bottomRoomNorthDoor);
            }
        }
    }

    private void lockEdgeDoors() {
        for (int i = 0; i < maze.length; i++) {
            // we want to lock the doors on the edge of the maze since there are no rooms beyond that
            maze[i][0].getDoor(Direction.WEST).lockDoor(); // left
            maze[i][maze[i].length - 1].getDoor(Direction.EAST).lockDoor(); // right
            maze[0][i].getDoor(Direction.NORTH).lockDoor(); // top
            maze[maze.length - 1][i].getDoor(Direction.SOUTH).lockDoor(); // bottom
        }
    }

    public Room getCurrentRoom() {
        return maze[characterRow][characterColumn];
    }

    public int getCharacterRow() {
        return characterRow;
    }

    public int getCharacterColumn() {
        return characterColumn;
    }
}
