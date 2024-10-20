package org.university.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.university.model.MD5;

@Controller
@RequestMapping("/hashing")
public class MD5Controller {

    @GetMapping()
    public String getMD5(Model model) {

        String message = "this is a much longer message that is designed to exceed 448 bits in length";
        String message2 = "hello";
        MD5 md5Algorithm = new MD5(message);
        String binaryMessage = md5Algorithm.getFullBinaryMessage();

        model.addAttribute("binaryMessage", binaryMessage);

        return "hashing";
    }
}
