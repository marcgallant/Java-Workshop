package fr.epita.assistants.cinematheque;

import java.util.*;
import java.util.function.Predicate;

public class ListStock<T> extends Stock<T> {

    public List<T> items;

    public ListStock() {
        items = new ArrayList<>();
    }

    public ListStock(List<T> items) {
        Set<T> set = new HashSet<>();
        for (T i : items) {
            if (set.contains(i)) {
                throw new IllegalArgumentException();
            } else {
                set.add(i);
            }
        }
        this.items = items;
    }


    @Override
    public boolean add(T t) {
        if (t != null && !items.contains(t)) {
            property.firePropertyChange("Add", t, Operation.Add); //FIXME
            return items.add(t);
        }
        return false;
    }

    @Override
    public boolean remove(T t) {
        if (t != null && items.contains(t)) {
            property.firePropertyChange("Delete", t, Operation.Delete); //FIXME
            return items.remove(t);
        }
        return false;
    }

    @Override
    public boolean contains(T t) {
        if (t == null)
            return false;
        return items.contains(t);
    }

    @Override
    public Collection<? extends T> list() {
        return items;
    }

    public boolean isSorted(Comparator<? super T> cmp) {
        for (int i = 0; i < items.size() - 1; i++) {
            int tmp = cmp.compare(items.get(i), items.get(i + 1));
            if (tmp > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean sort(Comparator<? super T> cmp) {
        if (cmp == null || isSorted(cmp))
            return false;

        property.firePropertyChange("Sort", items, Operation.Sort); //FIXME
        items.sort(cmp);
        return true;
    }

    @Override
    public Stock<T> filter(Predicate<? super T> p) {
        if (p == null)
            throw new IllegalArgumentException();
        return new ListStock<>(items.stream().filter(p).toList());
    }
}
