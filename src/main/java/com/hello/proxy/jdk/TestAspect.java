//package com.hello.proxy.jdk;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//
//@Aspect
//public class TestAspect {
//    //与非运算
//	//拦截类——方法
////	@Before("target(com.hello.proxy.jdk.WelcomeImpl)&&execution(* sayHello())")
//	//拦截类下的所有方法
//	@Before("target(com.hello.proxy.jdk.WelcomeImpl)")
//    public void notServeInNaiveWaiter(){
//        System.out.println(" before executed!--");
//    }
//	
//    //与运算
//	//within 拦截com.hello.proxy.jdk包下所有以Impl结尾的类，同时匹配sayHello()方法
//    @After("within(com.hello.proxy.jdk.*Impl)&&execution(* sayHello(..))")
//    public void greetToFun(){
//        System.out.println(" after executed!--");
//    }
//    
//    @Around("target(com.hello.proxy.jdk.WelcomeImpl)&&execution(* test(..))")
//	public Object processTx(ProceedingJoinPoint jp) throws Throwable{
//		System.out.println("执行目标方法之前，模拟开始事务...");
//		Object rvt=jp.proceed();
//		System.out.println("执行目标方法之后，模拟结束事务...");
//		return rvt+"新增的内容";
//	}
//
//    
////    //或运算
////    @AfterReturning("target(com.yyq.aspectJAdvanced.Waiter) || target(com.yyq.aspectJAdvanced.Seller)")
////    public void waiterOrSeller(){
////        System.out.println("--waiterOrSeller() executed!--");
////    }
//    
//}
