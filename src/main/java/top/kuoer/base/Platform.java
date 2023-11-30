package top.kuoer.base;

import top.kuoer.base.components.*;

public class Platform {

    private ComponentManage componentManage;

    public Platform() {
        this.componentManage = ComponentManage.getInstance();
    }

    public void registerDefaultComponents() {
        this.componentManage.registerComponent("Button", Button.class);
        this.componentManage.registerComponent("Text", Text.class);
        this.componentManage.registerComponent("Panel", Panel.class);
        this.componentManage.registerComponent("Input", Input.class);
        this.componentManage.registerComponent("CheckBox", CheckBox.class);
    }

    public ComponentManage getComponentManage() {
        return this.componentManage;
    }

}
