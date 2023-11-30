package top.kuoer.base.components;

import com.google.gson.Gson;
import top.kuoer.base.ComponentBase;
import top.kuoer.base.annotatios.Attribute;
import top.kuoer.base.pojo.Position;

import javax.swing.*;

public class Text extends ComponentBase {

    private JLabel label;

    private JButton button;

    @Override
    public void init() {

    }

    @Override
    public void onRegister() {
        this.label = new JLabel();
        this.setSuperComponent(this.label);
    }

    @Attribute("value")
    public void setValue(String value) {
        this.label.setText(value);
    }





}
