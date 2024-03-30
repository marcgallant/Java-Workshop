package fr.epita.assistants.seq;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public interface Seq<ELEMENT_TYPE> extends ExtendedStream<ELEMENT_TYPE> {

    static <TYPE> Seq<TYPE> of(final List<TYPE> values) {
        return new SeqImpl<>(values);
    }

    static <TYPE> Seq<TYPE> of(final TYPE[] values) {
        return new SeqImpl<>(values);
    }

    static <TYPE> Seq<TYPE> of(final Stream<TYPE> values) {
        return new SeqImpl<>(values);
    }

    default <KEY_TYPE> Map<KEY_TYPE, ELEMENT_TYPE> toMap(final Function<ELEMENT_TYPE, KEY_TYPE> keyMapper) {
        Map<KEY_TYPE, ELEMENT_TYPE> map = new HashMap<>();
        this.forEach(i -> {
            map.put(keyMapper.apply(i), i);
        });

        return map;
    }

    default <KEY_TYPE, VALUE_TYPE, MAP_TYPE extends Map<KEY_TYPE, VALUE_TYPE>> MAP_TYPE toMap(final MAP_TYPE map, final Function<ELEMENT_TYPE, KEY_TYPE> keyMapper, final Function<ELEMENT_TYPE, VALUE_TYPE> valueMapper) {
        this.forEach(i -> {
            map.put(keyMapper.apply(i), valueMapper.apply(i));
        });

        return map;
    }

    default <KEY_TYPE, VALUE_TYPE> Map<KEY_TYPE, VALUE_TYPE> toMap(final Function<ELEMENT_TYPE, KEY_TYPE> keyMapper, final Function<ELEMENT_TYPE, VALUE_TYPE> valueMapper) {
        Map<KEY_TYPE, VALUE_TYPE> map = new HashMap<>();
        this.forEach(i -> {
            map.put(keyMapper.apply(i), valueMapper.apply(i));
        });

        return map;
    }

    default List<ELEMENT_TYPE> toList() {
        List<ELEMENT_TYPE> list = new ArrayList<>();
        this.forEach(list::add);

        return list;
    }

    default <LIST extends List<ELEMENT_TYPE>> LIST toList(final LIST list) {
        this.forEach(list::add);

        return list;
    }

    default Set<ELEMENT_TYPE> toSet() {
        Set<ELEMENT_TYPE> set = new HashSet<>();
        this.forEach(set::add);

        return set;
    }

    default <SET extends Set<ELEMENT_TYPE>> SET toSet(final SET set) {
        this.forEach(set::add);

        return set;
    }

    default <ASSOCIATED_TYPE> ExtendedStream<Pair<ELEMENT_TYPE, ASSOCIATED_TYPE>> associate(final Supplier<ASSOCIATED_TYPE> supplier) {
        List<Pair<ELEMENT_TYPE,ASSOCIATED_TYPE>> list = new ArrayList<>();

        this.forEach(i -> list.add(new Pair<>(i, supplier.get())));
        return Seq.of(list);
    }

    default <ASSOCIATED_TYPE> ExtendedStream<Pair<ELEMENT_TYPE, ASSOCIATED_TYPE>> associate(final Stream<ASSOCIATED_TYPE> supplier) {
        List<Pair<ELEMENT_TYPE,ASSOCIATED_TYPE>> list = new ArrayList<>();
        Iterator<ASSOCIATED_TYPE> iterator = supplier.iterator();

        this.forEach(i -> list.add(new Pair<>(i, iterator.next())));
        return Seq.of(list);
    }

    default ExtendedStream<ELEMENT_TYPE> print() {
        this.forEach(i -> System.out.println(i.toString()));
        return this;
    }

    default ExtendedStream<ELEMENT_TYPE> plus(final Stream<ELEMENT_TYPE> stream) {
        List<ELEMENT_TYPE> list = new ArrayList<>();
        this.forEach(list::add);
        stream.forEach(list::add);
        return Seq.of(list);
    }

    default String join(final String delimiter) {
        StringBuilder res = new StringBuilder();
        List<ELEMENT_TYPE> list = toList();
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            res.append(list.get(i).toString()).append(delimiter);
        }
        if (list.size() != 0) res.append(list.get(list.size() - 1));

        return res.toString();
    }

    default String join() {
        StringBuilder res = new StringBuilder();
        for (ELEMENT_TYPE elementType : toList()) {
            res.append(elementType.toString());
        }
        return res.toString();
    }

    default <KEY_TYPE> ExtendedStream<Pair<KEY_TYPE, ExtendedStream<ELEMENT_TYPE>>> partition(final Function<ELEMENT_TYPE, KEY_TYPE> pivot) {
        List<Pair<KEY_TYPE, List<ELEMENT_TYPE>>> list = new ArrayList<>();

        this.forEach(i -> {
            KEY_TYPE type = pivot.apply(i);
            Boolean flag = true;

            //Check if already in list
            for (Pair<KEY_TYPE, List<ELEMENT_TYPE>> pair : list) {
                if (pair.first == type) {
                    flag = false;

                    List<ELEMENT_TYPE> tmp =  pair.second;
                    tmp.add(i);
                }
            }

            //Add to list
            if (flag) {
                List<ELEMENT_TYPE> tmp = new ArrayList<>();
                tmp.add(i);

                Pair<KEY_TYPE, List<ELEMENT_TYPE>> elt= new Pair<>(type, tmp);
                list.add(elt);
            }
        });

        //Convert
        List<Pair<KEY_TYPE, ExtendedStream<ELEMENT_TYPE>>> res = new ArrayList<>();
        for (Pair<KEY_TYPE, List<ELEMENT_TYPE>> pair : list) {
            res.add(new Pair<>(pair.first, Seq.of(pair.second)));
        }

        return Seq.of(res);

    }

    class SeqImpl<ELEMENT_TYPE> implements Seq<ELEMENT_TYPE> {

        private Stream<ELEMENT_TYPE> stream;

        public SeqImpl(List<ELEMENT_TYPE> value) {
            stream = value.stream();
        }

        public SeqImpl(ELEMENT_TYPE... value) {
            stream = Arrays.stream(value).distinct();
        }

        public SeqImpl(Stream<ELEMENT_TYPE> stream) {
            this.stream = stream;
        }

        @Override
        public Stream<ELEMENT_TYPE> filter(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.filter(predicate);
        }

        @Override
        public <R> Stream<R> map(Function<? super ELEMENT_TYPE, ? extends R> mapper) {
            return stream.map(mapper);
        }

        @Override
        public IntStream mapToInt(ToIntFunction<? super ELEMENT_TYPE> mapper) {
            return stream.mapToInt(mapper);
        }

        @Override
        public LongStream mapToLong(ToLongFunction<? super ELEMENT_TYPE> mapper) {
            return stream.mapToLong(mapper);
        }

        @Override
        public DoubleStream mapToDouble(ToDoubleFunction<? super ELEMENT_TYPE> mapper) {
            return stream.mapToDouble(mapper);
        }

        @Override
        public <R> Stream<R> flatMap(Function<? super ELEMENT_TYPE, ? extends Stream<? extends R>> mapper) {
            return stream.flatMap(mapper);
        }

        @Override
        public IntStream flatMapToInt(Function<? super ELEMENT_TYPE, ? extends IntStream> mapper) {
            return stream.flatMapToInt(mapper);
        }

        @Override
        public LongStream flatMapToLong(Function<? super ELEMENT_TYPE, ? extends LongStream> mapper) {
            return stream.flatMapToLong(mapper);
        }

        @Override
        public DoubleStream flatMapToDouble(Function<? super ELEMENT_TYPE, ? extends DoubleStream> mapper) {
            return stream.flatMapToDouble(mapper);
        }

        @Override
        public Stream<ELEMENT_TYPE> distinct() {
            return stream.distinct();
        }

        @Override
        public Stream<ELEMENT_TYPE> sorted() {
            return stream.sorted();
        }

        @Override
        public Stream<ELEMENT_TYPE> sorted(Comparator<? super ELEMENT_TYPE> comparator) {
            return stream.sorted(comparator);
        }

        @Override
        public Stream<ELEMENT_TYPE> peek(Consumer<? super ELEMENT_TYPE> action) {
            return stream.peek(action);
        }

        @Override
        public Stream<ELEMENT_TYPE> limit(long maxSize) {
            return stream.limit(maxSize);
        }

        @Override
        public Stream<ELEMENT_TYPE> skip(long n) {
            return stream.skip(n);
        }

        @Override
        public void forEach(Consumer<? super ELEMENT_TYPE> action) {
            stream.forEach(action);
        }

        @Override
        public void forEachOrdered(Consumer<? super ELEMENT_TYPE> action) {
            stream.forEachOrdered(action);
        }

        @Override
        public Object[] toArray() {
            return stream.toArray();
        }

        @Override
        public <A> A[] toArray(IntFunction<A[]> generator) {
            return stream.toArray(generator);
        }

        @Override
        public ELEMENT_TYPE reduce(ELEMENT_TYPE identity, BinaryOperator<ELEMENT_TYPE> accumulator) {
            return stream.reduce(identity, accumulator);
        }

        @Override
        public Optional<ELEMENT_TYPE> reduce(BinaryOperator<ELEMENT_TYPE> accumulator) {
            return stream.reduce(accumulator);
        }

        @Override
        public <U> U reduce(U identity, BiFunction<U, ? super ELEMENT_TYPE, U> accumulator, BinaryOperator<U> combiner) {
            return stream.reduce(identity, accumulator, combiner);
        }

        @Override
        public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super ELEMENT_TYPE> accumulator, BiConsumer<R, R> combiner) {
            return stream.collect(supplier, accumulator, combiner);
        }

        @Override
        public <R, A> R collect(Collector<? super ELEMENT_TYPE, A, R> collector) {
            return stream.collect(collector);
        }

        @Override
        public Optional<ELEMENT_TYPE> min(Comparator<? super ELEMENT_TYPE> comparator) {
            return stream.min(comparator);
        }

        @Override
        public Optional<ELEMENT_TYPE> max(Comparator<? super ELEMENT_TYPE> comparator) {
            return stream.max(comparator);
        }

        @Override
        public long count() {
            return stream.count();
        }

        @Override
        public boolean anyMatch(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.anyMatch(predicate);
        }

        @Override
        public boolean allMatch(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.allMatch(predicate);
        }

        @Override
        public boolean noneMatch(Predicate<? super ELEMENT_TYPE> predicate) {
            return stream.noneMatch(predicate);
        }

        @Override
        public Optional<ELEMENT_TYPE> findFirst() {
            return stream.findFirst();
        }

        @Override
        public Optional<ELEMENT_TYPE> findAny() {
            return stream.findFirst();
        }

        @Override
        public Iterator<ELEMENT_TYPE> iterator() {
            return stream.iterator();
        }

        @Override
        public Spliterator<ELEMENT_TYPE> spliterator() {
            return stream.spliterator();
        }

        @Override
        public boolean isParallel() {
            return stream.isParallel();
        }

        @Override
        public Stream<ELEMENT_TYPE> sequential() {
            return stream.sequential();
        }

        @Override
        public Stream<ELEMENT_TYPE> parallel() {
            return stream.parallel();
        }

        @Override
        public Stream<ELEMENT_TYPE> unordered() {
            return stream.unordered();
        }

        @Override
        public Stream<ELEMENT_TYPE> onClose(Runnable closeHandler) {
            return stream.onClose(closeHandler);
        }

        @Override
        public void close() {
            stream.close();
        }
    }
}
