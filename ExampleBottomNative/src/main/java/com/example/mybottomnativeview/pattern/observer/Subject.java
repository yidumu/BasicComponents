package com.example.mybottomnativeview.pattern.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Subject {
	
	public List<UpdateObserver> mObserver =new LinkedList<>();
	
	public void attach(UpdateObserver updateObserver){
		mObserver.add(updateObserver);
	}
	
	public void detach(UpdateObserver updateObserver){
		mObserver.remove(updateObserver);
	}
	
	protected abstract void notifyObservers();
	
	public abstract int getState();
	
	public abstract void setState(int state);

}
