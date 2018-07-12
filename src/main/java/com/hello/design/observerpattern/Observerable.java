package com.hello.design.observerpattern;

public interface Observerable {
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObserver();
}
