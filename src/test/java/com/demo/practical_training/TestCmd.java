package com.demo.practical_training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestCmd {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Process process = null;
        String shpath="ls -la";//.sh文件的绝对路径
        String command = "/bin/sh " + shpath;

        List<String> processList = new ArrayList<String>();

        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                processList.add(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : processList) {
            System.out.println(line);
        }

    }
}
