package fr.epita.assistants.myset;

import java.util.ArrayList;

public class GenericSet {
    private ArrayList<Comparable> base_;

    public GenericSet() {
        this.base_ = new ArrayList<Comparable>();
    }

    public void insert(Comparable i) {
        int j = 0;
        for (; j < base_.size(); j++) {
            if (base_.get(j).compareTo(i) == 0)
                return;

            if (base_.get(j).compareTo(i) > 0)
                break;
        }
        base_.add(j, i);
    }
    public void remove(Comparable i) {
        base_.remove(i);
    }
    public boolean has(Comparable i)
    {
        return base_.contains(i);
    }
    public boolean isEmpty() {
        return base_.size() == 0;
    }
    public Comparable min() {
        return base_.get(0);
    }
    public Comparable max() {
        return base_.get(base_.size() - 1);
    }
    public int size() {
        return base_.size();
    }
    public static GenericSet intersection(GenericSet a, GenericSet b) {
        GenericSet res = new GenericSet();

        for (int i = 0; i < a.size(); i++) {
            if (b.has(a.base_.get(i)))
                res.insert(a.base_.get(i));
        }

        return res;
    }
    public static GenericSet union(GenericSet a, GenericSet b) {
        for (int i = 0; i < b.size(); i++) {
            a.insert(b.base_.get(i));
        }
        return a;
    }
}
