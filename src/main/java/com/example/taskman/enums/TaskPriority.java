package com.example.taskman.enums;


public enum TaskPriority {

    HIGH("!!!", 1),
    MEDIUM("!!",2),
    LOW("!",3);

    private final String display;
    private final int order;

    TaskPriority(String display, int order){
        this.display = display;
        this.order = order;
    }

    public String getDisplay(){
        return display;
    }

    public int getOrder(){
        return order;
    }

    public static TaskPriority fromInput(String input) {
        for (TaskPriority taskPriority : values()) {
            if (taskPriority.display.equals(input)) {
                return taskPriority;
            }
        }
        throw new IllegalArgumentException("Invalid priority " + input);
    }

}
