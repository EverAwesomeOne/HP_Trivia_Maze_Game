package Model;

import java.util.LinkedList;

public class Maze {
    private final Room[][] myMaze;
    private int myCharacterRow;
    private int myCharacterColumn;
    private LinkedList<Integer>[] myRoomConnections;

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

    public boolean hasWon() {
        return myCharacterRow == myMaze.length - 1 && myCharacterColumn == myMaze.length - 1;
    }

    // performs a BFS traversal from the current room
    public boolean hasValidPaths() {
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

    private void connectSharedDoors() {
        // we want doors to adjacent rooms to be shared

        // east and west door of adjacent rooms
        for (final Room[] rowOfRooms : myMaze) {
            for (int singleRoom = 0; singleRoom < rowOfRooms.length - 2; singleRoom++) {
                final Door rightRoomWestDoor = rowOfRooms[singleRoom + 2].getDoor(Direction.WEST);
                rowOfRooms[singleRoom].setSharedDoor(Direction.EAST, rightRoomWestDoor);
            }
        }

        // north and south doors of adjacent rooms
        for (int i = 0; i < myMaze.length - 2; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                final Door bottomRoomNorthDoor = myMaze[i + 2][j].getDoor(Direction.NORTH);
                myMaze[i][j].setSharedDoor(Direction.SOUTH, bottomRoomNorthDoor);
            }
        }
    }

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

    public Room getCurrentRoom() {
        return myMaze[myCharacterRow][myCharacterColumn];
    }

    public int getCharacterRow() {
        return myCharacterRow;
    }

    public int getCharacterColumn() {
        return myCharacterColumn;
    }
}
