package top.kuoer.base;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import top.kuoer.base.annotatios.Attribute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentManage {

    public static ComponentManage componentManage;
    private final Map<String, Class> componentClassList = new HashMap<>();
    private final List<ComponentBase> componentBaseList = new ArrayList<>();

    private ComponentManage() {

    }

    public static ComponentManage getInstance() {
        if(null == componentManage) {
            componentManage = new ComponentManage();
        }
        return componentManage;
    }

    public void registerComponent(String componentName, Class componentClass) {
        this.componentClassList.put(componentName, componentClass);
    }

    public String getXmlString(String componentXmlFileName) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(".\\components\\" + componentXmlFileName);
        if(null != inputStream) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                StringBuffer sb = new StringBuffer();
                while((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public InputStream getXmlInputStream(String componentXmlFileName) {
        return this.getClass().getClassLoader().getResourceAsStream(".\\components\\" + componentXmlFileName);
    }

    public ComponentBase loadXmlToWindow(String xmlString) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(this.getXmlInputStream(xmlString));
            Element root = doc.getDocumentElement();

            return this.parseNode(this.componentBaseList, root);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ComponentBase parseNode(List<ComponentBase> componentBaseList, Node node) {
        ComponentBase currNodeComponentBase = null;

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            if(null != this.componentClassList.get(node.getNodeName())) {
                try {
                    // 将XML节点创建成组件
                    Object component = this.componentClassList.get(node.getNodeName()).getDeclaredConstructor().newInstance();

                    ComponentBase componentBase = (ComponentBase) component;
                    componentBase.setTagName(node.getNodeName());
                    componentBase.onRegister();

                    // 获取元素参数和内容，放入对应组件的方法
                    for (int i = 0; i < node.getAttributes().getLength(); i++) {
                        Node attr = node.getAttributes().item(i);

                        for(Method method : component.getClass().getMethods()) {
                            Attribute attribute = method.getAnnotation(Attribute.class);
                            if(null != attribute && attribute.value().equals(attr.getNodeName())) {
                                method.invoke(component, attr.getNodeValue());
                            }
                        }
                    }
                    componentBaseList.add(componentBase);

                    currNodeComponentBase = componentBase;
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // 递归处理子节点
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            ComponentBase componentBase = parseNode(componentBaseList, childNode);

            if(null != currNodeComponentBase && null != componentBase) {
                currNodeComponentBase.getSuperComponent().add(componentBase.getSuperComponent());
            }

        }
        return currNodeComponentBase;
    }

    public ComponentBase findComponentById(String id) {
        for(ComponentBase componentBase : componentBaseList) {
            if(id.equals(componentBase.getId())) {
                return componentBase;
            }
        }
        return null;
    }


}
