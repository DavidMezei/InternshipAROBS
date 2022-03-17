package week1.labor4.Exercise2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AuthorTest {

    @Test
    public void testToString(){
        Author author = new Author("George","george@hotmail.com",'m');
        assertEquals("George (m) at george@hotmail.com",author.toString());
    }
}
