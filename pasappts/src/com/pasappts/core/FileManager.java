package com.pasappts.core;

import com.pasappts.Main;
import com.pasappts.PasaPPTsException;
import com.pasappts.configuration.Configuration;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private static List<File> selectedFiles = new ArrayList<File>();

    public static List<File> getSelectedFiles() {
        return selectedFiles;
    }

    public static void openFileExplorer() throws PasaPPTsException {

        JFrame frame = new JFrame("Seleccionar archivos");
        frame.setAlwaysOnTop(true);

        JFileChooser fileChooser = new JFileChooser(Configuration.getPropertyValue("PPTS_HOME_PATH"));
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
            List<File> auxList = Arrays.asList(fileChooser.getSelectedFiles());
            selectedFiles.addAll(auxList);

            String fileName = "";
            for (File file : auxList) {
                fileName = file.getName().split("\\.")[0];
                Main.addItem(fileName);
            }
        }
    }

    public static void reproduce(String item) throws PasaPPTsException, IOException {

        File file = selectedFiles.stream().filter(i -> i.getName().contains(item)).findAny().orElse(null);
        if (file != null) {
            StringBuilder command = new StringBuilder();
            command.append("\"");
            command.append(Configuration.getPropertyValue("POWERPNT_EXECUTABLE"));
            command.append("\"");

            command.append(" /S ");

            command.append("\"");
            command.append(file.getAbsolutePath());
            command.append("\"");

            System.out.println("COMANDO: " + command.toString());

            Process process = Runtime.getRuntime().exec(command.toString());
        }
    }
}
