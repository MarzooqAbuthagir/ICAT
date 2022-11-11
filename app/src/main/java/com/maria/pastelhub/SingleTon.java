package com.maria.pastelhub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.maria.pastelhub.activity.choose.QAModel;
import com.maria.pastelhub.application.ICatApplication;
import com.maria.pastelhub.services.MusicService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SingleTon {

    public static void setListener(Context context, ImageView icon) {
        try {
            if (icon != null) {
                SharedPreferences preferences = context.getSharedPreferences("SharedPrefManager", Context.MODE_PRIVATE);
                icon.setImageResource(preferences.getBoolean("Sound", true) ? R.drawable.sound_on : R.drawable.sound_mute);
                icon.setOnClickListener(view -> {
                    icon.setImageResource(preferences.getBoolean("Sound", true) ? R.drawable.sound_mute : R.drawable.sound_on);
                    preferences.edit().putBoolean("Sound", !preferences.getBoolean("Sound", true)).apply();
                    if (ICatApplication.isMusicServiceRunning) {
                        preferences.edit().putString("MUSICVAL", "0").apply();
                        context.stopService(new Intent(context, MusicService.class));
                    } else {
                        preferences.edit().putString("MUSICVAL", "1").apply();
                        context.startService(new Intent(context, MusicService.class));
                    }
                });
                Log.i("--------", "-sing---" + preferences.getBoolean("Sound", true));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<QAModel> getChooseAnswerData(Context context, int classn, int lesson, String lag) {
        ArrayList<QAModel> models = new ArrayList<>();
        try {

            for (String model : getData(context, classn, lesson, "CHOOSE_ANSWER_", lag)) {
                try {
                    Log.i("jkdshdjshdkjs", "                  " + model);
                    String[] cur = model.split("##");
                    models.add(new QAModel(cur[0].replace("$$", "\n"), cur[1], cur[2], cur[3], cur[4], Integer.parseInt(cur[5]) - 1));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return models;
    }

    static String fileName = "";

    private static String[] getData(Context context, int classn, int lesson, String tag, String lan) {
        try {
            AssetManager am = context.getAssets();
            if (lan.equals("1")) {
                fileName = "english_class";
            } else fileName = "class_";
            InputStream is = am.open(fileName + classn + ".txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder result = new StringBuilder();
            boolean adddata = false;
            boolean lessonfound = false;
            if (lesson == 1)
                lessonfound = true;
            for (String line; (line = reader.readLine()) != null; ) {
                if (lessonfound && line.equals(tag + "END"))
                    break;
                if (!lessonfound)
                    lessonfound = line.equals("LESSON_" + lesson + "_START");
                if (adddata)
                    result.append(line).append("\n");
                if (!adddata)
                    adddata = lessonfound && line.equals(tag + "START");
            }

            return result.toString().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[0];
    }


    public static HashMap<String, String> getMatchData(Context context, int classn, int lesson, String lan) {

        HashMap<String, String> list = new HashMap<>();
        try {
            for (String obj : getData(context, classn, lesson, "MATCH_", lan)) {
                String[] l = obj.split("##");
                try {
                    list.put(l[0].trim(), l[1].trim());
                } catch (Exception e) {
                    //  e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }


    public static Object[] getWordSearchData(Context context, int classn, int lesson, String lan) {
        Object[] data = new Object[6];
        try {

            String[] result = getData(context, classn, lesson, "WORD_SEARCH_", lan)[0].split("##");
            data[0] = result[0];
            data[1] = result[0].replaceAll(", ", "").replaceAll("\\.", "").replaceAll(" ", "");
            data[2] = result[1].split(",");
            data[3] = Integer.parseInt(result[2]);
            data[4] = getSplitter(result[0]);
            if (result.length == 4)
                data[5] = result[3];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

    private static String getSplitter(String s) {
        s = s.trim()
                .replaceAll("  ", " ")
                .replaceAll(" , ", "ஹ்")
                .replaceAll(" \\. ", "ஹ்")
                .replaceAll(", ", "ஹ்").replaceAll(",", "ஹ்")
                .replaceAll("\\. ", "ஹ்")
                .replaceAll("\\.", "ஹ்").replaceAll(" ", "ஹ்");
        Pattern r = Pattern.compile("\\p{L}\\p{M}*");
        Matcher m = r.matcher(s);
        int current = 0;
        StringBuilder result = new StringBuilder();
        while (m.find()) {
            if (m.group().equals("ஹ்"))
                result.append(current).append(",");
            else
                current++;
        }
        return result.toString();
    }


    public static ArrayList<Object[]> getDragWordsData(Context context, int classn, int lesson, String lan) {
        ArrayList<Object[]> arrayList = new ArrayList<>();
        try {
            for (String obj : getData(context, classn, lesson, "DRAG_WORDS_", lan)) {
                try {
                    Log.i("==============000   ", "\n" + classn + "   " + lesson + "  " + lan);
                    String[] l = obj.split("@");
                    if (classn == 0 || classn == 1) {
                        Log.i("==============   ", "\n" + l[0]);
                        Object[] data = new Object[2];
                        data[0] = l[0].split("##");
                        data[1] = l[1];
                        arrayList.add(data);
//                        data[2] = l[2];
                    } else {
                        Object[] data = new Object[2];
                        data[0] = l[0].split("##");
                        data[1] = l[1];
                        arrayList.add(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}








