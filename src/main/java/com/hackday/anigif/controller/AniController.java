package com.hackday.anigif.controller;

import com.hackday.anigif.command.AniCommand;
import com.hackday.anigif.command.AnigifCommand;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AniController {
    AniCommand command = null;

    @RequestMapping(value = "/anigif", method = RequestMethod.POST, produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] anigif(HttpServletRequest request, Model model) {
        System.out.println("anigif()");

        model.addAttribute("request",request);
        command = new AnigifCommand();
        byte[] gif = command.execute(model);

        return gif;
    }


}
