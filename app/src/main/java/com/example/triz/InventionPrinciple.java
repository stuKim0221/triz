package com.example.triz;

public class InventionPrinciple {
    private int id;
    private String name;
    private String description;
    private String examples;

    public InventionPrinciple(int id, String name, String description, String examples) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.examples = examples;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExamples() {
        return examples;
    }
}
