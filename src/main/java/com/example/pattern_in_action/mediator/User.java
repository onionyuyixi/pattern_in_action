package com.example.pattern_in_action.mediator;

public abstract class User {

   public Mediator mediator;
   public String name;

    public User(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void speak(String msg);

    public abstract void listen(String msg);

}
