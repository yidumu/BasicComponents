package com.example.mybottomnativeview.pattern.observer;

public class ObserverSubject extends Subject {
    private int state;

    @Override
    protected void notifyObservers() {
        for (UpdateObserver mUpdateObserver : mObserver) {
            mUpdateObserver.update(this);
        }
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public void setState(int state) {
        this.state = state;
        this.notifyObservers();
    }
}
