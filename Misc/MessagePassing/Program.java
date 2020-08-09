/*
 * ----------------------------------------------------------------------
 * File:      Human.java
 * Project:   Messages
 * Author:    Sanjay Vyas
 * 
 * Description:
 *      Implement "message" passing in Java
 * ----------------------------------------------------------------------
 * Revision History:
 * 2020-May-30	[SV]: Created
 * ----------------------------------------------------------------------
 */

import java.util.HashMap;
import java.util.function.Consumer;

class Human {
    private String name;
    private HashMap<String, Consumer<String>> messageMap = new HashMap<>();

    public Human(String name) {
        this.name = name;

        // Populate message receivers
        // Use lambda
        messageMap.put("eat", (obj) -> System.out.println(name + " is eating " + obj));
        
        // Use function
        messageMap.put("sleep", this::humanSleeps);
    }

    private void humanSleeps(String time) {
        System.out.println(name + " has been sleeping for " + time);
    }

    public boolean message(String receiverName, String param) {
        Consumer<String> receiver = messageMap.get(receiverName);
        if (receiver != null) {
            receiver.accept(param);
            return true;
        }
        return false;
    }
}

class Program {
    public static void main(String[] args) {
        Human human = new Human("Gosling");

        // Dynamic language style message passing
        if (!human.message("eat", "banana"))
            System.out.println("Human doesnt understand eat");

        if (!human.message("sleep", "10 hours"))
            System.out.println("Human doesnt understand sleep");

        if (!human.message("getlost", "forever"))
            System.out.println("Human doesnt understand getlost");
    }
}
