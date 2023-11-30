package top.kuoer.base.components;

import top.kuoer.base.ComponentBase;
import top.kuoer.base.annotatios.Attribute;

import javax.swing.*;

public class Input extends ComponentBase {

    private JTextField jTextField;

    @Override
    public void init() {

    }

    @Override
    public void onRegister() {
        this.jTextField = new JTextField();
        this.setSuperComponent(jTextField);
    }

    @Attribute("value")
    public void setValue(String value) {
        this.jTextField.setText(value);
    }

}
