package ex1.tests;

import ex1.src.*;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WGraph_AlgomyTest {
    private weighted_graph hh;

    @Test
    void copy() {
        hh=checkingraph();
        weighted_graph_algorithms a = new WGraph_Algo();
        a.init(hh);
        weighted_graph b = a.copy();
        boolean c = hh.equals(b);
        Assertions.assertTrue(c);
    }

    @Test
    void isConnected() {
        hh=checkingraph();
        weighted_graph_algorithms a = new WGraph_Algo();
        a.init(hh);
        Assertions.assertTrue(a.isConnected());
        hh.removeNode(3);
        a.init(hh);
        Assertions.assertFalse(a.isConnected());
    }

    @Test
    void shortestPathDist() {
        hh=checkingraph();
        weighted_graph_algorithms a = new WGraph_Algo();
        a.init(hh);
        double q = a.shortestPathDist(0,6);
        Assertions.assertEquals(10,q);
        hh.connect(0,3,1.5);
        a.init(hh);
        q = a.shortestPathDist(0,6);
        Assertions.assertEquals(8.5,q);
        hh.removeNode(3);
        a.init(hh);
        q = a.shortestPathDist(0,6);
        Assertions.assertEquals(-1,q);
    }

    @Test
    void shortestPath() {
        hh=checkingraph();
        hh.addNode(8);
        hh.addNode(32);
        hh.connect(6,8,5);
        hh.connect(8,32,1);
        weighted_graph_algorithms a = new WGraph_Algo();
        a.init(hh);
        List<node_info> sp = a.shortestPath(0,32);
        int[] checkKey = {0, 3, 6, 8, 32};
        int i = 0;
        for(node_info n: sp) {
            //assertEquals(n.getTag(), checkTag[i]);
            Assertions.assertEquals(n.getKey(), checkKey[i]);
            i++;
        }
    }

    @Test
    void save() {
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
