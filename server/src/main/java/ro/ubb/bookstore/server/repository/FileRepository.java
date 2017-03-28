package ro.ubb.bookstore.server.repository;

import ro.ubb.bookstore.server.model.BaseEntity;
import ro.ubb.bookstore.server.model.validators.Validator;
import ro.ubb.bookstore.server.model.validators.ValidatorException;
import org.xml.sax.SAXException;
import ro.ubb.bookstore.server.util.XmlReader;
import ro.ubb.bookstore.server.util.XmlWriter;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.text.html.Option;
import javax.swing.text.html.parser.Entity;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * XML Repository
 */
public class FileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {
    private String fileName;
    public FileRepository(Validator<T> validator, String fileName) {
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
        ArrayList<T> arrayList = new ArrayList<T>();
        try {
            FileInputStream fileIn = new FileInputStream(this.fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            arrayList = (ArrayList<T>) in.readObject();
            for(T el : arrayList)
                super.save(el);
            in.close();
            fileIn.close();
        }catch(IOException i) {
            /// no such file
            System.out.println("No such file, will start from scratch!");
            return;
        }catch(ClassNotFoundException c) {
            return;
        }
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> optional = super.save(entity);
        dumpToFile();
        return optional;
    }

    private void dumpToFile() {
         try {
             FileOutputStream fileOut = new FileOutputStream(this.fileName);
             ObjectOutputStream out = new ObjectOutputStream(fileOut);
             ArrayList<T> arrayList = new ArrayList<T>();
             StreamSupport.stream(findAll().spliterator(), false)
                     .forEach(e -> {
                        arrayList.add(e);
                     });
             out.writeObject(arrayList);
             out.close();
             fileOut.close();
             System.out.printf("Serialized data is saved in: " + this.fileName);
        }catch(IOException i) {
             i.printStackTrace();
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
