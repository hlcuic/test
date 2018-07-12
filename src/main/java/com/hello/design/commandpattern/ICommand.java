package com.hello.design.commandpattern;

public interface ICommand {
	void execute();
	//撤销动作
	void undo();  
}
