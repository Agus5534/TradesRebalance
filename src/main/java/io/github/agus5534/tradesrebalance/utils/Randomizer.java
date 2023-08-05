package io.github.agus5534.tradesrebalance.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    @SafeVarargs
    public static <T> T get(T... objects) {
        return get(Arrays.asList(objects));
    }

    public static <T> T get(List<T> list) {
        checkEmpty(list);

        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static <T> List<T> shuffle(int times, List<T> list) {
        int n = 0;
        List<T> newList = new ArrayList<>(list);

        while (n < Math.abs(times)) {
            newList = get(newList.size(), newList);

            n++;
        }

        return newList;
    }

    public static <T> List<T> get(int count, List<T> list) {
        count = Math.abs(count);

        List<T> l = new ArrayList<>(list);

        int n = 0;

        checkEmpty(list);
        checkSize(list, count);

        List<T> returnList = new ArrayList<>();

        while (n < count) {
            T element = get(l);
            l.remove(element);
            returnList.add(element);

            n++;
        }

        return returnList;
    }

    @SafeVarargs
    public static <T> List<T> get(int count, T... objects) {
        return get(count, Arrays.asList(objects));
    }

    private static <T> void checkEmpty(List<T> list) {
        if(list.isEmpty()) {
            throw new IndexOutOfBoundsException("List is empty.");
        }
    }

    private static <T> void checkSize(List<T> list, int size) {
        if(list.size() < size) {
            throw new IndexOutOfBoundsException(String.format("Requested %s objects but list has %s", size, list.size()));
        }
    }


}
