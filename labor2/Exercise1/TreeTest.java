package labor2.Exercise1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TreeTest {

    @Test
    public void testGrow(){
        Tree tree=new Tree();
        tree.grow(2);
        assertEquals("Should increase tree height", 17, Integer.parseInt(tree.toString()));
    }

    @Test
    public void testGrow2(){
        Tree tree=new Tree();
        tree.grow(-1);
        assertEquals("Should not change anything",15,Integer.parseInt(tree.toString()));
    }

    @Test
    public void testGrow3(){
        Tree tree=new Tree();
        assertEquals("Should give back 15",15,Integer.parseInt(tree.toString()));
    }
}
