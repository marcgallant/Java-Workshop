package fr.epita.assistants.myset;

import java.util.ArrayList;

public class IntegerSet {
    public ArrayList<Integer> base_;

    public IntegerSet() {
        this.base_ = new ArrayList<>();
    }

    public void insert(Integer i) {
        int j = 0;
        for (; j < base_.size(); j++) {
            if (base_.get(j).equals(i))
                return;

            if (base_.get(j) > i)
                break;
        }

        base_.add(j, i);
    }
    public void remove(Integer i) {
        base_.remove(i);
    }
    public boolean has(Integer i)
    {
        return base_.contains(i);
    }
    public boolean isEmpty() {
        return base_.size() == 0;
    }
    public Integer min() {
        return base_.get(0);
    }
    public Integer max() {
        return base_.get(base_.size() - 1);
    }
    public int size() {
        return base_.size();
    }
    public static IntegerSet intersection(IntegerSet a, IntegerSet b) {
        IntegerSet res = new IntegerSet();

        for (int i = 0; i < a.size(); i++) {
            if (b.has(a.base_.get(i)))
                res.insert(a.base_.get(i));
        }

        return res;
    }
    public static IntegerSet union(IntegerSet a, IntegerSet b) {
        for (int i = 0; i < b.size(); i++) {
            a.insert(b.base_.get(i));
        }
        return a;
    }
}
