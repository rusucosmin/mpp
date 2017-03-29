package ro.ubb.bookstore.server.repository;

import ro.ubb.bookstore.common.model.BaseEntity;
import ro.ubb.bookstore.common.model.validators.Validator;
import ro.ubb.bookstore.common.model.validators.ValidatorException;
import org.xml.sax.SAXException;
import ro.ubb.bookstore.server.util.XmlReader;
import ro.ubb.bookstore.server.util.XmlWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

/**
 * XML Repository
 */
public class XmlRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {
    private String fileName;
    public XmlRepository(Validator<T> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        try {
            loadData();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws SAXException, IllegalAccessException, IOException, NoSuchFieldException, InstantiationException, ParserConfigurationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        System.out.println("loading file..");
        List<T> data = new XmlReader<ID, T>(this.fileName).loadEntities();
        for(T el : data) {
            try {
                System.out.println("saving: " + el.toString());
                super.save(el);
            } catch(ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> optional = super.save(entity);
        dumpToFile();
        return optional;
    }

    private void dumpToFile() {
        XmlWriter<ID, T> xmlWriter = new XmlWriter<ID, T>(this.fileName);
        try {
            xmlWriter.clearFile();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for(T el : super.findAll())
            try {
                System.out.println("dumping to file " + el.toString());
                xmlWriter.save(el);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<T> optional = super.delete(id);
        dumpToFile();
        return optional;
    }

    @Override
    public Optional<T> update(T entity) {
        Optional<T> optional = super.update(entity);
        dumpToFile();
        return optional;
    }
}
