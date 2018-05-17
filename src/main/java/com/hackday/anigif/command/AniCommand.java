package com.hackday.anigif.command;

import com.hackday.anigif.model.ImageModel;



public interface AniCommand {
    byte[] execute(ImageModel image);
}
