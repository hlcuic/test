package com.hello.design.statuspattern;

/**
 * 状态模式：状态改变，行为也会随之发生改变,避免出现太多的if...else语句,
 * 影响后期维护
 * @author Administrator
 *
 */
public class TestDemo {
	public static void main(String[] args) {
		Context context = new Context();
		State startState = new StartState();
		startState.doAction(context);
		System.out.println(context.getState().toString());
		
		State stopState = new StopState();
		stopState.doAction(context);
		System.out.println(context.getState().toString());
	}
}
