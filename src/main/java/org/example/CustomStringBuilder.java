package org.example;

import java.util.Stack;

public class CustomStringBuilder {
    private StringBuilder stringBuilder;
    private final Stack<SnapShot> history;

    public CustomStringBuilder() {
        this.stringBuilder = new StringBuilder();
        this.history = new Stack<>();
    }

    public CustomStringBuilder append(String str) {
        saveState();
        stringBuilder.append(str);
        return this;
    }

    public CustomStringBuilder delete(int start, int end) {
        saveState();
        stringBuilder.delete(start, end);
        return this;
    }

    public CustomStringBuilder insert(int offset, String str) {
        saveState();
        stringBuilder.insert(offset, str);
        return this;
    }

    public CustomStringBuilder replace(int start, int end, String str) {
        saveState();
        stringBuilder.replace(start, end, str);
        return this;
    }

    public void undo() {
        if (!history.isEmpty()) {
            SnapShot snapShot = history.pop();
            this.stringBuilder = new StringBuilder(snapShot.getState());
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    private void saveState() {
        history.push(new SnapShot(stringBuilder.toString()));
    }

    private class SnapShot {
        private final String state;

        public SnapShot(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}