package repository;

import model.BaseEntity;
import model.validators.Validator;
import model.validators.ValidatorException;
import org.xml.sax.SAXException;
import util.XmlReader;
import util.XmlWriter;

import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
        List<T> data = new XmlReader<ID, T>(this.fileName).loadEntities();
        for(T el : data) {
            try {
                super.save(el);
            } catch(ValidatorException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> optional = super.findOne(entity.getID());
        if(optional.isPresent())
            return optional;
        try {
            System.out.println("save to file ");
            new XmlWriter<ID, T>(this.fileName).save(entity);
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
        return optional;
    }

    @Override
    public Optional<T> delete(ID id) {
        Optional<T> optional = super.delete(id);
        if(!optional.isPresent())
            return optional;
        XmlWriter<ID, T> xmlWriter = new XmlWriter<ID, T>(this.fileName);
        try {
            System.out.println("delet(ID id) clear file");
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
                System.out.println("delte(DI id) put in file");
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
        return optional;
    }

    @Override
    public Optional<T> update(T entity) {
        Optional<T> optional = super.update(entity);
        if(!optional.isPresent())
            return optional;
        XmlWriter<ID, T> xmlWriter = new XmlWriter<ID, T>(this.fileName);
        try {
            System.out.println("clear file");
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
                System.out.println("put in file");
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
        return optional;
    }
}
