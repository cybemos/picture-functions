package com.cybemos.client.commands;

public interface Command<Args> {

    void execute(Args args);

}
