package ro.ubb.bookstore.server.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseEntityTest {

    BaseEntity <Integer> intModel;
    BaseEntity <String> stringModel;

    @Before
    public void setUp() {
        intModel = new BaseEntity<>();
        stringModel = new BaseEntity<>();
    }

    @Test
    public void testIntModel() {
        intModel.setID(1);
        assertEquals(intModel.getID(), new Integer(1));
        intModel.setID(intModel.getID() + 1);
        assertEquals(intModel.getID(), new Integer(2));
    }

    @Test
    public void testStringModel() {
        stringModel.setID("test_id");
        assertEquals(stringModel.getID(), "test_id");
        stringModel.setID("id_test");
        assertEquals(stringModel.getID(), "id_test");
    }
}
