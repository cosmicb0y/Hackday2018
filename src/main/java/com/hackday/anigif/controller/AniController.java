package com.hackday.anigif.controller;

import com.hackday.anigif.command.AniCommand;
import com.hackday.anigif.command.AnigifCommand;
import com.hackday.anigif.command.MergeCommand;
import com.hackday.anigif.command.ResizeCommand;
import com.hackday.anigif.model.ImageModel;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.*;
import java.util.regex.Pattern;


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

    @RequestMapping(value = "/resize/{name:.+}", method = RequestMethod.GET)
    public @ResponseBody byte[] resize(@PathVariable String name, @RequestParam(value = "width") int width, @RequestParam(value = "height") int height) {
        System.out.println("resize()");

        command = new ResizeCommand();
        ImageModel imageModel = new ImageModel(name, width, height);

        byte[] resizedImage = command.execute(imageModel);

        return resizedImage;
    }

    @RequestMapping(value = "/merge/{name:.+}", method = RequestMethod.GET)
    public @ResponseBody byte[] merge(@PathVariable String name, @RequestParam(value = "image") String param) {
        System.out.println("merge()");

        ArrayList<String> imageList = new ArrayList<>();

        imageList.add(name);
        imageList.add(param);

        String[] gifExtention = {"gif"};

        for (int i = 0; i < imageList.size(); i++) {
            if (!checkExtension(imageList.get(i), gifExtention)) {
                System.out.println("There is image file doesn't gif");
                return null;
            }
        }

        command = new MergeCommand();
        ImageModel imageModel = new ImageModel(imageList);
        byte[] mergeGif = command.execute(imageModel);

        return mergeGif;
    }


    public boolean checkExtension(String filename, String[] extensionList) {
        String ext = FilenameUtils.getExtension(filename);
        if (Arrays.asList(extensionList).contains(ext)) {
            return true;
        }
        return false;
    }


}
