package com.company.javaHomework;

public class Animal {
    private String name;
    private int age;
    private int weight;
    private String color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я ем");
    }

    @Override
    public String toString() {
        return "Привет! Меня зовут " + name + ", мне " + age + " " + getRightNameOfTheYear(age) + ", я вешу - " + weight + " кг, мой цвет - " + color;
    }

    private String getRightNameOfTheYear(int age) {
        String nameOfTheYear = "";
        int modulo = age % 10;
        if (modulo == 1) {
            nameOfTheYear = "год";
        } else if ((modulo == 0 || (modulo >= 5 && modulo <= 9)) || (age % 100 >= 11) && (age % 100 <= 14)) {
            nameOfTheYear = "лет";
        } else if (modulo >= 2 && modulo <= 4) {
            nameOfTheYear = "года";
        }
        return nameOfTheYear;
    }
}
