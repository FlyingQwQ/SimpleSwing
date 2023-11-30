package top.kuoer.base.components;

import top.kuoer.base.ComponentBase;
import top.kuoer.base.annotatios.Attribute;

import javax.swing.*;

public class CheckBox extends ComponentBase {

    private JCheckBox jCheckBox;

    @Override
    public void init() {

    }

    @Override
    public void onRegister() {
        this.jCheckBox = new JCheckBox();
        this.setSuperComponent(this.jCheckBox);
    }

    @Attribute("value")
    public void setValue(String value) {
        this.jCheckBox.setText(value);
    }

}
