//package com.company.javaHomework;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//public class Application {
//
//    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//
//        ArrayList<Animal> animals = new ArrayList<>();
//        mainMenu(animals);
//    }
//
//    private static void mainMenu(ArrayList<Animal> animals) throws InstantiationException, IllegalAccessException {
//
//        System.out.println("\nЗдравствуйте! Выберите значение из меню и введите его в консоль");
//        System.out.println("Чтобы добавить животное введите - add");
//        System.out.println("Чтобы показать всех имеющихся животных введите - list");
//        System.out.println("Чтобы выйти из приложения введите - exit");
//
//        Scanner scanner = new Scanner(System.in);
//        Commands command = null;
//
//        command = getRightCommandFromUser(scanner, command);
//
//        while (!(command.toString().equals("EXIT"))) {
//            switch (command) {
//                case ADD:
//                    commandAdd(animals, scanner);
//                    mainMenu(animals);
//                    break;
//
//                case LIST:
//                    for (Animal animal :
//                            animals) {
//                        System.out.println(animal.toString());
//                    }
//
//                    mainMenu(animals);
//                    break;
//
//                case EXIT:
//                    break;
//            }
//        }
//    }
//
//    private static Commands getRightCommandFromUser(Scanner scanner, Commands command) {
//        try {
//            command = Commands.valueOf(scanner.nextLine().toUpperCase().trim());
//        } catch (RuntimeException e) {
//            System.out.println("Такой команды не существует");
//            return getRightCommandFromUser(scanner, command);
//        }
//        return command;
//    }
//
//    private static void commandAdd(ArrayList<Animal> animals, Scanner scanner) throws InstantiationException, IllegalAccessException {
//        System.out.println("Какое животное вы хотите добавить? Вы можете добавить: 1 - cat, 2 - dog, 3 - duck");
//        TypesOfAnimals typeOfAnimal = TypesOfAnimals.getAnimalByNumber(scanner.nextInt());
//
//        Animal animal = (Animal) typeOfAnimal.getAnimal().newInstance();
//
//        System.out.println("Какое имя будет у этого животного?");
//        String animalName = scanner.next();
//        animal.setName(animalName);
//
//        System.out.println("Какой возраст у этого животного?");
//        int animalAge = scanner.nextInt();
//        animal.setAge(animalAge);
//
//        System.out.println("Какой вес у этого животного?");
//        int animalWeight = scanner.nextInt();
//        animal.setWeight(animalWeight);
//
//        System.out.println("Каким цветом это животное?");
//        String animalColor = scanner.next();
//        animal.setColor(animalColor);
//
//        animals.add(animal);
//        animal.say();
//    }
//}
