package com.bytecode;

public class PersonService {

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        Person person = new Person("Mouli", 25);
        String name = person.getName();
        System.out.print(name);
    }
}