package top.kuoer.base;

import javax.swing.*;
import java.io.IOException;

public class Window extends JFrame{

    private Platform platform;
    private String componentXmlFileName;

    public Window(Platform platform, String componentXmlFileName) {
        this.platform = platform;
        this.componentXmlFileName = componentXmlFileName;

        this.init();
    }

    private void init() {
        ComponentBase component = this.platform.getComponentManage().loadXmlToWindow(this.componentXmlFileName);
        this.add(component.getSuperComponent());
    }




}
