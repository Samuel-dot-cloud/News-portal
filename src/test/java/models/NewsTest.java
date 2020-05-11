package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getContent_returnsCorrectContent()throws Exception {
        News testNews=createGeneralNews();
        assertEquals("Company's profits are at an all time high!!",testNews.getContent());
    }


    @Test
    public void setContent_returnsCorrectContent()throws Exception {
        News testNews=createGeneralNews();
        testNews.setContent("company has losses");
        assertNotEquals("Company's profits are at an all time high!!",testNews.getContent());
    }

    //    HELPER
    public News createGeneralNews()
    {
        return new News("Company's profits are at an all time high!!", 1, "sales");
    }
}