package fr.epita.assistants;

import fr.epita.assistants.myset.IntegerSet;

public class Main {
    public static void main(String[] args) {
        IntegerSet my_set = new IntegerSet();
        my_set.insert(1);
        my_set.insert(2);
        my_set.insert(3);
        System.out.println("My set contains " + my_set.size() + " element(s)");

        System.out.println(my_set.has(1));

        System.out.println(my_set.base_.get(0));
        System.out.println(my_set.base_.get(1));
        System.out.println(my_set.base_.get(2));
    }
}