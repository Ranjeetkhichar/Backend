package org.example.Generics;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Pair<Integer, String> p = new Pair<>(10, "Hello");
        Pair<Integer, Double> p2 = new Pair<>(10, 10.2);

        Pair.<String>some(new String());
        Pair.some(10);
        Pair.some(10.2);
        Pair.<Object>some(new Object());

        Pair.<Object>doNothing(new Object(), 1);

        Pair.<String, Pair>doSomething("Ranjeet", p);

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());

        somefun(animals);

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog());
//        dogs.add(new Cat()); // cats can be animal not dog
//        dogs.add(new Animal()); // As animals can't be dog always

        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());

        animals.addAll(cats);
//        cats.addAll(animals);

//        animals = dogs; //Can't replace but can add all dogs to animals.
        animals.addAll(dogs);



        //        PECS -> Producer extends, Consumer Super


        //Upper bound Generics
        List<? extends Animal> extendedAnimals = new ArrayList<>();
        extendedAnimals = dogs; // Any ArrayList of class that extends Animal or itself Animal
        printAllAnimalNames(extendedAnimals);
        extendedAnimals = cats;
        printAllAnimalNames(extendedAnimals);
        extendedAnimals = animals;
        printAllAnimalNames(extendedAnimals);

        Animal animal = extendedAnimals.get(0);
        Dog dog = extendedAnimals.get(0);
        Object object = extendedAnimals.get(0);

//        extendedAnimals.add(new Dog());
//        extendedAnimals.add(new Animal());


        //Lower bound Generics -> Only Dog and parent of Dog is allowed
        List<? super Dog> superDogs = new ArrayList<>();
        superDogs = dogs; //=> Any ArrayList of class which is Dog or parent/superClass of Dog.
        superDogs = cats;
        superDogs = animals;

        Dog d = superDogs.get(0);
        Animal a = superDogs.get(0);
        Object obj = superDogs.get(0);



    }

    static void somefun(List<Animal> animals){
        animals.add(new Dog());
        animals.add(new Cat());
    }

    static void addDog(Dog d, List<? super Dog> superDogs){
        superDogs.add(d);
    }

    static void printAllAnimalNames(List<? extends Animal> animals){
        for (Animal animal : animals){
            System.out.println(animal.getName());
            animal.makeNoise();
        }
    }
}
