package org.university.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class RandomNumbersFileWriter {

    public void write(List<Integer> numbers, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            int count = 0;
            for (Integer number : numbers) {
                writer.write(number + " ");
                count++;
                if (count % 20 == 0) {
                    writer.write("\n");
                }
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
