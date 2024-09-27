package org.university.model;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {

    private InputData data;

    public Randomizer(InputData data) {
        this.data = data;
    }

    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();

        int x = data.getX0();
        for(int i = 1; i < data.getOutputNumber(); i++) {
            x = (data.getA() * x + data.getC()) % data.getM();
            numbers.add(x);
        }

        return numbers;
    }
}
