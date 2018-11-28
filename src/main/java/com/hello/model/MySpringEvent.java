package com.hello.model;

import org.springframework.context.ApplicationEvent;

public class MySpringEvent extends ApplicationEvent {

    private String name;

    /**
     * 异步调用很方便。比如说
     */
    public MySpringEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

