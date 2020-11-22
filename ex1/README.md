This project deals with weighted graphs.
A weighted graph is a graph of nodes that exist in a graph, a connection between two nodes will be made by using an edge that has a weight.
The project implements the three interfaces node_info, weighted_graph and weighted_graph_algorithms,
Node_Info is an internal class within the WGraph_DS class which implements the node_info interface,
this class builds nodes and knows how to change/retrieve information from node.
WGraph_DS is a class which implement weighted_graph, this class knows how to change/retrieve information from weighted graph.
WGraph_Algo is a class which implements weighted_graph_algorithms, this class knows to answer the questions: if this whole graph is connected, what the shortest
path beetween two nodes (by calculating weight), what is the minimum weight path beetween two nodes, in addition this class cal save and load weighted graph
as a file.
