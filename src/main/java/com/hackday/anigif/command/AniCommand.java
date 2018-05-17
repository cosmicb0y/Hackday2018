package com.hackday.anigif.command;

import org.springframework.ui.Model;


public interface AniCommand {
    byte[] execute(Model model);
}
