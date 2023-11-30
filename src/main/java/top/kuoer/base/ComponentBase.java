package top.kuoer.base;


import com.google.gson.Gson;
import top.kuoer.base.annotatios.Attribute;
import top.kuoer.base.pojo.Position;
import top.kuoer.base.pojo.Size;

import javax.swing.*;

public abstract class ComponentBase {

    public String name = "";
    public String id = "";
    public String tagName;
    private JComponent jcomponent;


    public abstract void init();

    public abstract void onRegister();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    @Attribute("id")
    public void setId(String id) {
        this.id = id;
    }

    public void setSuperComponent(JComponent jcomponent) {
        this.jcomponent = jcomponent;
    }

    public JComponent getSuperComponent() {
        return this.jcomponent;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Attribute("position")
    public void setPosition(String json) {
        Gson gson = new Gson();
        Position position = gson.fromJson(json, Position.class);
        this.jcomponent.setLocation(position.getX(), position.getY());
    }

    @Attribute("size")
    public void setSize(String json) {
        Gson gson = new Gson();
        Size size = gson.fromJson(json, Size.class);
        this.jcomponent.setSize(size.getWidth(), size.getHeight());
    }

}
