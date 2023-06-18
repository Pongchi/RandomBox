package org.pongchi.randombox;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Box {
    @SuppressWarnings("unchecked")
    private static JSONObject readFile() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(System.getProperty("user.dir")+"/plugins/RandomBox/boxes.json");
            Object obj = parser.parse(reader);
            reader.close();
            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return new JSONObject();
    }

    @SuppressWarnings("unchecked")
    private static void saveFile(JSONObject boxes) {
        try {
            FileWriter file = new FileWriter(System.getProperty("user.dir")+"/plugins/RandomBox/boxes.json");
            file.write(boxes.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void create(Player p, String name) {
        JSONObject boxes = readFile();
        if (boxes.containsKey(name)) {
            p.sendMessage("[랜덤박스] 해당 이름의 랜덤박스가 존재합니다.");
        }
        else {
            boxes.put(name, new JSONArray());
            saveFile(boxes);
            p.sendMessage("[랜덤박스] 랜덤박스 생성 완료!");
        }
    }
    @SuppressWarnings("unchecked")
    public static boolean addItem(String boxName, ItemStack item) {
        System.out.println(item.getType().name() + " " + item.getData());
        if (item.getType() == Material.AIR)
            return false;

        JSONObject boxes = readFile();
        JSONArray itemList = (JSONArray) boxes.get(boxName);
        itemList.add(item);

        boxes.put(boxName, itemList);
        saveFile(boxes);
        return true;
    }
}
