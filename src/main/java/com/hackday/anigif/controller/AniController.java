package com.hackday.anigif.controller;

import com.hackday.anigif.command.AniCommand;
import com.hackday.anigif.command.AnigifCommand;
import com.hackday.anigif.model.ImageModel;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.Arrays;


@RestController
@EnableWebMvc
public class AniController {
    AniCommand command = null;

    @RequestMapping(value = "/anigif", method = RequestMethod.POST, produces = MediaType.IMAGE_GIF_VALUE)
    public byte[] anigif(@RequestBody String request) {
        System.out.println("anigif()");

        JSONObject obj = new JSONObject(request);
        JSONArray jsonArr = obj.getJSONArray("paths");
        ArrayList<String> imgList = new ArrayList<>();
        for (int i = 0; i < jsonArr.length(); i++) {
            imgList.add(jsonArr.get(i).toString());
        }

        int delay = obj.getInt("delay");

        command = new AnigifCommand();
        ImageModel imageModel = new ImageModel(imgList, delay);

        for (int i = 0; i < imgList.size(); i++) {
            if (!checkExtension(imageModel.getImageList().get(i), imageModel.getExtentionList())) {
                System.out.println("There is a file doesn't supply");
                return null;
            }
        }

        byte[] gif = command.execute(imageModel);

        return gif;
    }

    public boolean checkExtension(String filename, String[] extensionList) {
        String ext = FilenameUtils.getExtension(filename);
        if (Arrays.asList(extensionList).contains(ext)) {
            return true;
        }
        return false;
    }


}
