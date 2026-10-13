
package DoubleLinkedList;

/**
 * Implement an application that supports undo/redo functionality.
 * Use a linked list to maintain a sequence of states.
 * Each state change is stored as a node in the list,
 * allowing easy navigation.
 *
 * 1 <-> 2 <-> 3 <-> 4 <-> 5
 */

public class UndoRedoManager<T> {

    private class Node {
        private final T state;
        private Node next;
        private Node previous;

        private Node(T state) {
            this.state = state;
        }
    }

    private Node currentState;

    // Undo operation
    public T Undo() {
        if (currentState == null) {
            System.out.println("No state to undo");
            return null;
        }

        Node previousState = currentState.previous;

        if (previousState == null) {
            System.out.println("Reached the initial start state");
        } else {
            currentState = previousState;
        }

        return currentState.state;
    }

    // Perform an operation and add a new state
    public void addState(T newState) {
        Node newNode = new Node(newState);

        // Set the links for the new node
        newNode.previous = currentState;
        newNode.next = null;

        // Update the next link for the current state
        if (currentState != null) {
            currentState.next = newNode;
        }

        // Update the current state
        currentState = newNode;
    }

    // Redo operation
    public T Redo() {
        if (currentState == null) {
            System.out.println("No state to redo");
            return null;
        }

        if (currentState.next == null) {
            System.out.println("No next state to redo");
            return currentState.state;
        }

        currentState = currentState.next;
        return currentState.state;
    }

    public static void main(String[] args) {
        UndoRedoManager<String> undoRedoManager =
                new UndoRedoManager<>();

        undoRedoManager.addState("Wake up from bed");
        undoRedoManager.addState("Brush Teeth");
        undoRedoManager.addState("Take a Shower");
        undoRedoManager.addState("Go to Class");

        System.out.println("Current State: "
                + undoRedoManager.currentState.state);

        // Test undo
        System.out.println("Undo: " + undoRedoManager.Undo());
        System.out.println("Undo: " + undoRedoManager.Undo());

        // Test redo
        System.out.println("Redo: " + undoRedoManager.Redo());

        System.out.println("Current State: "
                + undoRedoManager.currentState.state);

        System.out.println("Redo: " + undoRedoManager.Redo());

        System.out.println("Current State: "
                + undoRedoManager.currentState.state);
    }
}

