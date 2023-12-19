package ca.ulaval.glo2004.domain;

import java.io.*;
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
        undoHistory.peek().restore();
    }

    public void redo() {
        if (!canRedo()) return;
        ChaletMemento memento = redoHistory.pop();
        undoHistory.push(memento);
        undoHistory.peek().restore();
    }

    public boolean canUndo() {
        return !undoHistory.isEmpty();
    }

    public boolean canRedo() {
        return !redoHistory.isEmpty();
    }

    public Chalet clone(Chalet chalet) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(chalet);
            oos.close();

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Chalet clone = (Chalet) ois.readObject();
            ois.close();
            return clone;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
