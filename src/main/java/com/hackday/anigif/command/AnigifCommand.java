package com.hackday.anigif.command;

import com.hackday.anigif.model.AniImage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;

public class AnigifCommand implements AniCommand{

    @Override
    public byte[] execute(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        StringBuilder builder = null;
        byte[] gifByte;

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(request.getInputStream()));
            builder = new StringBuilder();
            String buffer;
            while ((buffer = input.readLine()) != null) {
                if (builder.length() > 0) {
                    builder.append("\n");
                }
                builder.append(buffer);
            }
        } catch (IOException e) {

        } finally {
            JSONObject obj = new JSONObject(builder.toString());
            JSONArray jsonArr = obj.getJSONArray("paths");
            ArrayList<String> imgList = new ArrayList<>();
            for (int i = 0; i < jsonArr.length(); i++) {
                imgList.add(jsonArr.get(i).toString());
            }
            int delay = obj.getInt("delay");

            AniImage AniGifMaker = new AniImage();
            gifByte = AniGifMaker.makeGif(imgList, delay);

        }
        return gifByte;
    }
}
