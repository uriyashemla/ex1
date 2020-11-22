package ex1.src;

import java.io.Serializable;
import java.util.*;

public class WGraph_DS implements weighted_graph, Serializable {
    private HashMap<Integer,node_info> graph;
    private HashMap<Integer,HashMap<Integer,Double>> Nighbors;
    private int Edgesize;
    private int Size;
    private int MC;

    public WGraph_DS(){
        this.graph = new HashMap<Integer, node_info>();
        this.Nighbors = new HashMap<Integer,HashMap<Integer,Double>>();
    }

    /**
     * this function check if there is Node with the same key in the Graph and than return this Node
     * @param key - the node_id
     * @return if this key exist in the graph return this Node, if it doesn't return null
     */
    @Override
    public node_info getNode(int key) {
        if(this.graph.containsKey(key)){
            return this.graph.get(key);
        }
        return null;
    }

    @Override
    public boolean hasEdge(int node1, int node2) {
        if(this.Nighbors.get(node1).containsKey(node2)&&this.Nighbors.get(node2).containsKey(node1)){
            return true;
        }
        return false;
    }

    @Override
    public double getEdge(int node1, int node2) {
        if(hasEdge(node1,node2)){
            double d = this.Nighbors.get(node1).get(node2).doubleValue();
            return d;
        }
        return -1;
    }

    @Override
    public void addNode(int key) {
        if(!this.graph.containsKey(key)){
            this.graph.put(key, new NodeInfo(key));
            this.Nighbors.put(key,new HashMap<Integer, Double>());
            this.Size++;
            this.MC++;
        }
    }

    @Override
    public void connect(int node1, int node2, double w) {
        if(this.graph.containsKey(node1)&&this.graph.containsKey(node2)&&node1!=node2) {
            if (!this.Nighbors.containsKey(node1)) {
                this.Nighbors.put(node1, new HashMap<Integer, Double>());
            }
            if (!this.Nighbors.containsKey(node2)) {
                this.Nighbors.put(node2, new HashMap<Integer, Double>());
            }
            if (!hasEdge(node1, node2)) {
                this.Nighbors.get(node1).put(node2, w);
                this.Nighbors.get(node2).put(node1, w);
                this.Edgesize++;
            }
            else{
                this.Nighbors.get(node1).replace(node2,w);
                this.Nighbors.get(node2).replace(node1,w);
            }
            this.MC++;
        }
    }

    @Override
    public Collection<node_info> getV() {
        return this.graph.values();
    }

    @Override
    public Collection<node_info> getV(int node_id) {
        if(this.graph.containsKey(node_id)) {
            Collection<node_info> nei = new ArrayList<>();
            if (this.Nighbors.get(node_id).keySet() != null) {
                Iterator<Integer> itr = this.Nighbors.get(node_id).keySet().iterator();
                while (itr.hasNext()) {
                    nei.add(this.graph.get(itr.next()));
                }
                return nei;
            }
        }
        return null;
    }

    @Override
    public node_info removeNode(int key) {
        if(this.graph.containsKey(key)){
            node_info remo = this.getNode(key);
            if(this.Nighbors.containsKey(key)) {
                Iterator<Integer> itr = this.Nighbors.get(key).keySet().iterator();
                while (itr.hasNext()) {
                    this.Nighbors.get(itr.next()).remove(key);
                    this.Edgesize--;
                }
                this.Nighbors.remove(key);
            }
            this.MC++;
            this.Size--;
            this.graph.remove(key);
            return remo;
        }
        return null;
    }

    @Override
    public void removeEdge(int node1, int node2) {
        if(hasEdge(node1,node2)){
            this.Nighbors.get(node1).remove(node2);
            this.Nighbors.get(node2).remove(node1);
            this.Edgesize--;
            this.MC++;
        }
    }

    @Override
    public int nodeSize() {
        return this.Size;
    }

    @Override
    public int edgeSize() {
        return this.Edgesize;
    }

    @Override
    public int getMC() {
        return this.MC;
    }

    private class NodeInfo implements node_info, Serializable{
        private int key;
        private String Info;
        private double Tag;

        public NodeInfo(int key){
            this.key = key;
            this.Info = "White";
            this.Tag = -1;
        }

        @Override
        public int getKey() {
            return this.key;
        }

        @Override
        public String getInfo() {
            return this.Info;
        }

        @Override
        public void setInfo(String s) {
            this.Info = s;
        }

        @Override
        public double getTag() {
            return this.Tag;
        }

        @Override
        public void setTag(double t) {
            this.Tag=t;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeInfo nodeInfo = (NodeInfo) o;
            return key == nodeInfo.key &&
                    Double.compare(nodeInfo.Tag, Tag) == 0 &&
                    Info.equals(nodeInfo.Info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, Info, Tag);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WGraph_DS wGraph_ds = (WGraph_DS) o;
        return Edgesize == wGraph_ds.Edgesize &&
                Size == wGraph_ds.Size &&
                graph.equals(wGraph_ds.graph) &&
                Nighbors.equals(wGraph_ds.Nighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graph, Nighbors, Edgesize, Size, MC);
    }
}
