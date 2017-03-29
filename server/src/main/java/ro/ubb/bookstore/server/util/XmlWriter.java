package ro.ubb.bookstore.server.util;

import ro.ubb.bookstore.common.model.BaseEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Appends an entity of type T with id of type ID to an existing xml file.
 * The xml structure is assumed to be like the one from data/students.xml.
 *
 */
public class XmlWriter<ID, T extends BaseEntity<ID>> {

    private String fileName;

    public XmlWriter(String fileName) {
        this.fileName = fileName;
    }

    private static void removeEmptyText(Node node){
        Node child = node.getFirstChild();
        while(child!=null){
            Node sibling = child.getNextSibling();
            if(child.getNodeType()==Node.TEXT_NODE){
                if(child.getTextContent().trim().isEmpty())
                    node.removeChild(child);
            } else
                removeEmptyText(child);
            child = sibling;
        }
    }

    public void save(T entity) throws ParserConfigurationException, IOException, SAXException, IllegalAccessException, TransformerException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.fileName);
        Element root = document.getDocumentElement();
        removeEmptyText(root);

        Node entityNode = createNodeFromEntity(document, root, entity);
        root.appendChild(entityNode);

        //save in file
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(document),new StreamResult(new File(this.fileName)));
    }

    private Node createNodeFromEntity(Document document, Element root, T entity) throws IllegalAccessException {
        Node entityNode = document.createElement(entity.getClass().getName());
        ((Element) entityNode).setAttribute("id", entity.getID().toString());

        for(Field f : entity.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            appendChildNodeWithText(document, entityNode, f.getName(), f.get(entity).toString());
        }

        return entityNode;
    }

    private static void appendChildNodeWithText(Document document, Node parent, String tagName, String value) {
        Node node = document.createElement(tagName);
        node.setTextContent(value);

        parent.appendChild(node);
    }

    public void clearFile() throws ParserConfigurationException, TransformerException, IOException, SAXException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.fileName);
        Element root = document.getDocumentElement();

        while(root.hasChildNodes())
            root.removeChild(root.getFirstChild());

        //save in file
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource(document),new StreamResult(new File(this.fileName)));
    }
}
