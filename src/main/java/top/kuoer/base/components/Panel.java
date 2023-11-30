package top.kuoer.base.components;

import top.kuoer.base.ComponentBase;
import top.kuoer.base.annotatios.Attribute;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Panel extends ComponentBase {

    private JPanel jPanel;
    private Map<String, LayoutManager> layoutManagerMap = new HashMap<>();

    @Override
    public void init() {

    }

    @Override
    public void onRegister() {
        this.jPanel = new JPanel();
        this.setSuperComponent(this.jPanel);

        this.layoutManagerMap.put("auto", null);
        this.layoutManagerMap.put("BorderLayout", new BorderLayout());
        this.layoutManagerMap.put("GridLayout", new GridLayout());
        this.layoutManagerMap.put("FlowLayout", new FlowLayout());
        this.layoutManagerMap.put("CardLayout", new CardLayout());
        this.layoutManagerMap.put("GridBagLayout", new GridBagLayout());
    }

    @Attribute("layout")
    public void setLayout(String value) {
        this.jPanel.setLayout(this.layoutManagerMap.get(value));
    }

}
