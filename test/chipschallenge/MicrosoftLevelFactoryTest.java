/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chipschallenge;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author patrik
 */
public class MicrosoftLevelFactoryTest {

    MicrosoftLevelFactory instance = MicrosoftLevelFactory.getInstance();

    public MicrosoftLevelFactoryTest() {}

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of getLastLevelNumber method, of class MicrosoftLevelFactory.
     */
    @Test
    public void testGetLastLevelNumber() {
        assertEquals(149, instance.getLastLevelNumber());
    }

    @Test
    public void TestGetLevelOffset() throws IOException {
        assertEquals(6, instance.getLevelOffset(1));
        assertEquals(317, instance.getLevelOffset(2));
    }

    @Test
    public void TestGetLevelPassword() throws IOException {
        assertEquals("BDHP", instance.getLevelPassword(1));
        assertEquals("JXMJ", instance.getLevelPassword(2));
        assertEquals("DIGW", instance.getLevelPassword(149));
    }

    /**
     * Test of getLevelNumberByPassword method, of class MicrosoftLevelFactory.
     */
    @Test
    public void testGetLevelNumberByPassword() {
        assertEquals(73, instance.getLevelNumberByPassword("QCCR"));
    }

}