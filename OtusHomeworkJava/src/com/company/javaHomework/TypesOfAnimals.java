package com.company.javaHomework;

public enum TypesOfAnimals {
    CAT("Cat", 1, Cat.class),
    DOG("Dog", 2, Dog.class),
    DUCK("Duck", 3, Duck.class);

    private String type;
    private int number;
    private Class animal;

    TypesOfAnimals(String type, int number, Class clazz){
        this.type = type;
        this.number = number;
        this.animal = clazz;
    }

    public static TypesOfAnimals getAnimalByNumber (int number){
        for (TypesOfAnimals animal:
        TypesOfAnimals.values()) {
            if (animal.number == number){
                return animal;
            }
        }
        throw new IllegalArgumentException("No animal found");
    }

    public String getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }

    public Class getAnimal() {
        return animal;
    }
}
