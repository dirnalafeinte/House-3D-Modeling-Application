package ca.ulaval.glo2004.domain;

import java.util.Stack;

public class ChaletCaretaker {
    private final Stack<ChaletMemento> undoHistory = new Stack<>();
    private final Stack<ChaletMemento> redoHistory = new Stack<>();

    public void addMemento(ChaletMemento memento) {
        undoHistory.push(memento);
        redoHistory.clear();
    }

    public void undo() {
        if (!canUndo()) return;
        ChaletMemento memento = undoHistory.pop();
        redoHistory.push(memento);
        memento.restore();
    }

    public void redo() {
        if (!canRedo()) return;
        ChaletMemento memento = redoHistory.pop();
        undoHistory.push(memento);
        memento.restore();
    }

    public boolean canUndo() {
        return !undoHistory.isEmpty();
    }

    public boolean canRedo() {
        return !redoHistory.isEmpty();
    }
}
