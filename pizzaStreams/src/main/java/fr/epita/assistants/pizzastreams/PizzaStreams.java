package fr.epita.assistants.pizzastreams;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import fr.epita.assistants.pizzastreams.Topping.*;

public class PizzaStreams {
    /**
     * @return The sum of the prices of all the pizzas in the stream
     */
    public static Integer getTotalPrice(Stream<Pizza> pizzaStream) {
        return pizzaStream
                .map(Pizza::getPrice)
                .reduce(0, Integer::sum);
    }

    /**
     * @return The average price of the pizzas in the stream, or the
     * double NaN if the stream is empty
     */
    public static Double getAveragePrice(Stream<Pizza> pizzaStream) {
        List<Pizza> list = pizzaStream.toList();
        Stream<Pizza> tmp = list.stream();
        Stream<Pizza> tmp2 = list.stream();

        long len = tmp.count();

        long total = getTotalPrice(tmp2);
        return (double)total / len;
    }

    /**
     * @return Names of the pizzas, sorted by price in ascending order
     */
    public static List<String> sortByPrice(Stream<Pizza> pizzaStream) {
        return pizzaStream
                .sorted(Comparator.comparing(Pizza::getPrice))
                .map(Pizza::getName).toList();
    }

    /**
     * @return The Pizza object that has the lowest price, or null by default
     */
    public static Pizza getCheapest(Stream<Pizza> pizzaStream) {
        Optional<Pizza> res = pizzaStream.
                min(Comparator.comparing(Pizza::getPrice));
        return res.orElse(null);
    }

    /**
     * @return Names of the pizzas with meat (Protein)
     */
    public static List<String> getCarnivorous(Stream<Pizza> pizzaStream) {
        return pizzaStream
                .filter(pizza -> pizza.getTopping().getProtein().isPresent())
                .map(Pizza::getName).toList();
    }

    /**
     * @return Names of the pizzas with at least one Vegetable and no Proteins
     */
    public static List<String> getVeggies(Stream<Pizza> pizzaStream) {
        return pizzaStream
                .filter(pizza -> !pizza.getTopping().getVegetableList().isEmpty() && !pizza.getTopping().getProtein().isPresent())
                .map(Pizza::getName).toList();
    }

    /**
     * @return true if all the pizzas with a nature dough are based with tomato
     * and mozzarella (italian pizza criteria), false otherwise
     */
    public static boolean areAllNatureItalians(Stream<Pizza> pizzaStream) {
        return pizzaStream.allMatch(pizza -> pizza.getTopping().getSauce() == Sauce.TOMATO && pizza.getTopping().getCheese() == Cheese.MOZZARELLA);
    }
}
