package org.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.university.model.InputData;
import org.university.model.Randomizer;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/randomizer")
public class InputController {

    @GetMapping("/input")
    public String getInput() {
        return "input";
    }

    @PostMapping("/output")
    public String submitOutput(@ModelAttribute @Valid InputData inputData, Model model, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "input";
        }

        Randomizer randomizer = new Randomizer(inputData);
        List<Integer> numbers = randomizer.generate();
        model.addAttribute("numbers", numbers);
        return "randomizer-output";
    }

}
