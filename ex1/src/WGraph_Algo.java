package ex1.src;

import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms, Serializable{
    private weighted_graph G;

    @Override
    public void init(weighted_graph g) {
        this.G=g;
    }

    @Override
    public weighted_graph getGraph() {
        return g;
    }

    /**
     *  this function creates a new graph that will contain all the copies of the nodes
     *  that exist in the ancient graph,
     *  in addition it connects all the edges in the new graph as they are in the ancient graph.
     * @return the new copied graph
     */
    @Override
    public weighted_graph copy() {
        if (this.G != null) {
            weighted_graph op = new WGraph_DS();
            Iterator<node_info> itr = this.G.getV().iterator();
            while (itr.hasNext()) {
                node_info w = itr.next();;
                op.addNode(w.getKey());
            }
            Iterator<node_info> itr1 = this.G.getV().iterator();
            while (itr1.hasNext()) {
                node_info t = itr1.next();
                Iterator<node_info> itr2 = this.G.getV(t.getKey()).iterator();
                while (itr2.hasNext()) {
                    node_info zq = itr2.next();
                    op.connect(zq.getKey(), t.getKey(), this.G.getEdge(zq.getKey(), t.getKey()));
                }
            }
            return op;
        }
        return null;
    }
    /**
     * this function went to the first node in the graph list,
     * changed its information to be "black" (instead of "white" as it initialized),
     * and added it to the queue.
     * then theres a loop that would pull out the next node in the queue
     * and add all its neighbors to the queue and change its information to be black
     * and so on until the queue is empty.
     * then it go through the whole graph and make sure that the information of all the nodes is black,
     * if there is even one that is not black I know it is not connected to the other nodes
     * because if it was connected its color would turn black.
     * Then ill reset all the Info to be "white" again.
     * @return true if the graph is connected or false if it isnt connected.
     */
    @Override
    public boolean isConnected() {
        if (this.G.nodeSize() <= 1) {
            return true;
        }
        Queue<node_info> Tor = new LinkedList<node_info>();
        boolean flag = true;
        Iterator<node_info> itr = this.G.getV().iterator();
        node_info h = itr.next();
        h.setInfo("Black");
        Tor.add(h);
        while (!Tor.isEmpty()) {
            node_info cur = Tor.poll();
            Iterator<node_info> itr1 = this.G.getV(cur.getKey()).iterator();
            while (itr1.hasNext()) {
                node_info nex = itr1.next();
                if (nex.getInfo().equals("White")) {
                    Tor.add(nex);
                    nex.setInfo("Black");
                }
            }
        }
        while (itr.hasNext()) {
            if (itr.next().getInfo().equals("White")) {
                flag = false;
            }
        }
        Iterator<node_info> itr3 = this.G.getV().iterator();
        while (itr3.hasNext()) {
            itr3.next().setInfo("White");
        }
        return flag;
    }

    /**
     * this function built a queue and put the src node in it and change its tag to 0
     * then I made a loop that pulls the first node in the queue and joins all its neighbors
     * to the queue and changes their tag as their weight from the first node (the function
     * know how to update a Node even after it has been initialized previously by bigger number),
     * after the queue Empty all the nodes that linked to the src received a new tag
     * according to their distance (weight) from the src,
     * and than return the tag of the dest node and this is the shortest distance from the src to it.
     * Then ill reset all the tags in the graph to be -1 and the info to white.
     * @param src - start node
     * @param dest - end (target) node
     * @return the shortest path between src to dest, if there isnt path between them return -1.
     */
    @Override
    public double shortestPathDist ( int src, int dest){
        double sh = -1;
        if(G.getNode(src)==null||G.getNode(dest)==null){
            return sh;
        }
        Queue<node_info> q = new LinkedList<node_info>();
        q.add(G.getNode(src));
        q.peek().setTag(0);
        while (!q.isEmpty()) {
            node_info cur = q.poll();
            if (!cur.getInfo().equals("Black")) {
                double x = cur.getTag();
                Iterator<node_info> itr1 = this.G.getV(cur.getKey()).iterator();
                while (itr1.hasNext()) {
                    node_info nex = itr1.next();
                    if (nex.getInfo().equals("White")) {
                        q.add(nex);
                        nex.setInfo("Grey");
                    }
                    if (nex.getTag() > (x + this.G.getEdge(nex.getKey(), cur.getKey())) || nex.getTag() == -1) {
                            nex.setTag((x + this.G.getEdge(nex.getKey(), cur.getKey())));
                        }
                }
                cur.setInfo("Black");
            }
        }
        if(G.getNode(src).getTag()!=-1&&G.getNode(dest).getTag()!=-1) {
            sh = G.getNode(dest).getTag();
        }

        Iterator<node_info> itr3 = this.G.getV().iterator();
        while (itr3.hasNext()) {
            node_info rem = itr3.next();
            rem.setInfo("White");
            rem.setTag(-1);
        }
        return sh;
    }
    /**
     * this function will change the tag of all the nodes that linked to src just like in the previous function,
     * then It builds a stack (because the method is LIFO) and put the dest in it, and then
     * by loop it will look for neighbor whose his tag is match to the weight of this node
     * minus the edge wheigh, and so on until I get to the src.
     * than it pop all the nodes from the stack into a list,
     * then reset the tag and info to all of the graph nodes
     * and return the list
     * @param src - start node
     * @param dest - end (target) node
     * @return list of the shortest path if there isnt path at all it will return null
     */
    @Override
    public List<node_info> shortestPath ( int src, int dest){
        if(G.getNode(src)==null||G.getNode(dest)==null){
            return null;
        }
        Queue<node_info> Torino = new LinkedList<node_info>();
        Torino.add(G.getNode(src));
        Torino.peek().setTag(0);
        while (!Torino.isEmpty()) {
            node_info cur = Torino.poll();
            if (!cur.getInfo().equals("Black")) {
                double x = cur.getTag();
                Iterator<node_info> itr1 = this.G.getV(cur.getKey()).iterator();
                while (itr1.hasNext()) {
                    node_info nex = itr1.next();
                    if (nex.getInfo().equals("White")) {
                        Torino.add(nex);
                        nex.setInfo("Grey");
                    }
                    if (nex.getTag() > (x + this.G.getEdge(nex.getKey(), cur.getKey())) || nex.getTag() == -1) {
                        nex.setTag((x + this.G.getEdge(nex.getKey(), cur.getKey())));
                    }
                }
                cur.setInfo("Black");
            }
        }
        Stack<node_info> M = new Stack<node_info>();
        if(G.getNode(src).getTag()!=-1&&G.getNode(dest).getTag()!=-1) {
            node_info des = G.getNode(dest);
            while (true){
                if(des.getTag()==0){
                    M.add(des);
                    break;
                }
                Iterator<node_info> nini = this.G.getV(des.getKey()).iterator();
                while (nini.hasNext()) {
                    node_info sho = nini.next();
                    double d = this.G.getEdge(des.getKey(),sho.getKey());
                    double e = sho.getTag();
                    double f = des.getTag();
                    if (f==e+d){
                        M.add(des);
                        des = sho;
                        break;
                    }
                }
            }
        }
        else{
            return null;
        }
        List<node_info> path = new LinkedList<>();
        while (!M.isEmpty()) {
            path.add(M.pop());
        }
        Iterator<node_info> itr3 = this.G.getV().iterator();
        while (itr3.hasNext()) {
            node_info rem = itr3.next();
            rem.setInfo("White");
            rem.setTag(-1);
        }
        return path;
    }

    /**
     * This function create a new File and saves a weighted graph into it.
     * @param file - the file name (may include a relative path).
     * @return if it managed to save return true, else false
     */
    @Override
    public boolean save(String file) {
        System.out.println("starting Serialize to "+file+"\n");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this.G);

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("end of Serialize \n\n");
        return true;
    }

    /**
     * This function loads a file (graph) intp a graph.
     * @param file - file name
     * @return if it managed to load return true, else false
     */
    @Override
    public boolean load(String file) {
        System.out.println("Deserialize from : "+file);
        // Deserialize HashMap of user-defined object values
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            this.G = (weighted_graph)objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
