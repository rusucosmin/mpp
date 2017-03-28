package ro.ubb.bookstore.server.util;

import ro.ubb.bookstore.server.model.BaseEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Loads all entities from an xml file containing entities of type T with id of type ID.
 *
 */
public class XmlReader<ID, T extends BaseEntity<ID>> {
    private String fileName;

    public XmlReader(String fileName) {
        this.fileName = fileName;
    }

    public List<T> loadEntities() throws ParserConfigurationException, IOException, SAXException, ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<T> entities = new ArrayList<>();
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.fileName);
        Node root = document.getDocumentElement();

        NodeList nodeList = root.getChildNodes();
        for(int i = 0; i < nodeList.getLength(); ++ i) {
            Node node = nodeList.item(i);
            if(node instanceof Element) {
                T el = createBaseEntityFromNode(node);
                entities.add(el);
            }
        }

        return entities;
    }

    private static String getTextContentByTagName(Element node, String tagName) {
        NodeList nodeList = node.getElementsByTagName(tagName);
        Node now = nodeList.item(0);

        return now.getTextContent();
    }

    private T createBaseEntityFromNode(Node node) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        String className = node.getNodeName();
        Class entityClass = Class.forName(className);

        T entity = (T) entityClass.newInstance();

        Field idField = entityClass.getSuperclass().getDeclaredField("id");
        String id = ((Element) node).getAttribute("id");

        idField.setAccessible(true);
        idField.set(entity, new Integer(id));

        for(Field f : entityClass.getDeclaredFields()) {
            f.setAccessible(true);
            Class fieldClass = f.getType();
            Constructor<?> constructor = fieldClass.getConstructor(String.class);
            Object o = constructor.newInstance(getTextContentByTagName((Element) node, f.getName()));
            f.set(entity, o);
        }

        return entity;
    }
}
