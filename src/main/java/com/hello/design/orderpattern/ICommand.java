package com.hello.design.orderpattern;

public interface ICommand {
	void execute();
	//撤销动作
	void undo();  
}
