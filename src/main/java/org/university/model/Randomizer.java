package org.university.model;

import java.util.ArrayList;
import java.util.List;

public class Randomizer {

    private InputRandomizerData data;

    public Randomizer(InputRandomizerData data) {
        this.data = data;
    }

    public List<Integer> generate() {
        List<Integer> numbers = new ArrayList<>();

        int x = data.getX0();
        numbers.add(x);
        for(int i = 1; i < data.getOutputNumber(); i++) {
            x = (data.getA() * x + data.getC()) % data.getM();
            numbers.add(x);
        }

        return numbers;
    }

    public int findPeriod(List<Integer> numbers) {
        int skipNumber = 1;

        boolean isContinue = true;
        int period = -1;
        while(isContinue) {
            period = findTempPeriod(numbers, skipNumber);
            System.out.println("Period is - " + period + ", skip number is - " + skipNumber);

            boolean isPeriod = true;
            for (int j = 0; j < period; j++) {
                if (!numbers.get(j).equals(numbers.get(period + j))) {
                    isPeriod = false;
                    skipNumber++;
                    break;
                }
            }
            if (isPeriod) {
                isContinue = false;
            }
        }

        return period;
    }

    private int findTempPeriod(List<Integer> numbers, int skipNumber) {
        return numbers.stream()
                .skip(skipNumber)
                .filter(num -> num.equals(numbers.get(0)))
                .findFirst()
                .map(numbers.subList(1, numbers.size())::indexOf)
                .map(index -> index + 1)
                .orElse(-1);
    }
}
