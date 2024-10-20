package org.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.university.model.InputRandomizerData;
import org.university.model.Randomizer;
import org.university.service.RandomNumbersFileWriter;

import java.util.List;

@Controller
@RequestMapping("/randomizer")
public class InputRandomizerController {

    RandomNumbersFileWriter randomNumbersFileWriter;

    @Autowired
    public InputRandomizerController(RandomNumbersFileWriter randomNumbersFileWriter) {
        this.randomNumbersFileWriter = randomNumbersFileWriter;
    }

    @GetMapping("/input")
    public String getInput() {
        return "input";
    }

    @PostMapping("/output")
    public String submitOutput(@ModelAttribute InputRandomizerData inputRandomizerData, Model model) {

        Randomizer randomizer = new Randomizer(inputRandomizerData);
        List<Integer> numbers = randomizer.generate();
        int period = randomizer.findPeriod(numbers);

        model.addAttribute("numbers", numbers);
        model.addAttribute("period", period);

        randomNumbersFileWriter.write(numbers, "C:\\university\\7-sem\\security\\numbers.txt");
        return "randomizer-output";
    }

}
