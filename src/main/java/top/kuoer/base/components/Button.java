package top.kuoer.base.components;

import top.kuoer.base.ComponentBase;
import top.kuoer.base.annotatios.Attribute;

import javax.swing.*;

public class Button extends ComponentBase {

    private JButton button;


    @Override
    public void init() {

    }

    @Override
    public void onRegister() {
        this.button = new JButton();
        this.setSuperComponent(this.button);
    }

    @Attribute("value")
    public void setValue(String value) {
        this.button.setText(value);
    }

}
