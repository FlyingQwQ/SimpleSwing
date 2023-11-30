package top.kuoer;


import top.kuoer.base.Platform;
import top.kuoer.base.Window;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Platform platform = new Platform();
        platform.registerDefaultComponents();

        Window window = new Window(platform, "mainWindow.xml");
        window.setSize(500, 350);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        JButton jButton = (JButton) platform.getComponentManage().findComponentById("loginBtn").getSuperComponent();
        jButton.addActionListener((e) -> {

        });

    }
}