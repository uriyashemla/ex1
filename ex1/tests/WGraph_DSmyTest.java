package ex1.tests;

import ex1.src.WGraph_DS;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class WGraph_DSmyTest {
    private weighted_graph hh;

    @Test
    void hasEdge() {
        hh = checkingraph();
        boolean m = hh.hasEdge(0,2);
        Assertions.assertTrue(m);
        hh.removeEdge(0,2);
        m = hh.hasEdge(0,2);
        Assertions.assertFalse(m);

    }

    @Test
    void getEdge() {
        hh = checkingraph();
        double a = hh.getEdge(0,1);
        Assertions.assertEquals(1,a);
        hh.removeEdge(0,1);
        a= hh.getEdge(0,1);
        Assertions.assertEquals(-1,a);
    }


    @Test
    void connect() {
        hh = checkingraph();
        hh.connect(0,30,7);
        hh.connect(0,6,9);
        int s = hh.edgeSize();
        Assertions.assertEquals(7,s);
        double d = hh.getEdge(0,6);
        Assertions.assertEquals(9,d);
        hh.connect(0,6,7);
        d=hh.getEdge(0,6);
        Assertions.assertEquals(7,d);
    }

    @Test
    void getV() {
        hh = checkingraph();
        Collection<node_info> v = hh.getV();
        Iterator<node_info> iter = v.iterator();
        while (iter.hasNext()) {
            node_info n = iter.next();
            Assertions.assertNotNull(n);
        }
    }

    @Test
    void nodeSize() {
        hh = checkingraph();
        int s = hh.nodeSize();
        Assertions.assertEquals(7,s);
        hh.removeNode(0);
        hh.removeNode(12);
        s = hh.nodeSize();
        Assertions.assertEquals(6,s);
    }

    @Test
    void edgeSize() {
        hh = checkingraph();
        int s = hh.edgeSize();
        Assertions.assertEquals(6,s);
        hh.removeNode(0);
        hh.removeEdge(1,4);
        hh.removeEdge(1,3);
        s= hh.edgeSize();
        Assertions.assertEquals(2,s);
    }

    public weighted_graph checkingraph(){
        weighted_graph gg = new WGraph_DS();
        gg.addNode(0);
        gg.addNode(1);
        gg.addNode(2);
        gg.addNode(3);
        gg.addNode(4);
        gg.addNode(5);
        gg.addNode(6);
        gg.connect(0,1,1);
        gg.connect(0,2,2);
        gg.connect(0,3,3);
        gg.connect(1,4,0.6);
        gg.connect(2,5,8);
        gg.connect(3,6,7);
        return gg;
    }
}
