package a4;

public class ExampleTest4 {
	public static void main(String[] args) {
		GraphFactory graphfact = new GraphFactory();
		Graph graph = graphfact.create(11);
		NodeFactory nodefact = new NodeFactory();
		Node a = nodefact.create("node 1");
		Node b = nodefact.create("node 2");
		Node c = nodefact.create("node 3");
		Node d = nodefact.create("node 4");
		Node e = nodefact.create("node 5");
		Node f = nodefact.create("node 6");
		Node g = nodefact.create("node 7");
		Node h = nodefact.create("node 8");
		Node i = nodefact.create("node 9");
		Node j = nodefact.create("node 10");
		Node k = nodefact.create("node 11");
		
		graph.addNodes(a,b,c,d,e,f,g,h,i,j,k);
		
		graph.addEdge(i,j);
		graph.addEdge(h, i);
		graph.addEdge(f, h);
		graph.addEdge(g, f);
		graph.addEdge(e, f);
		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(d, c);
		graph.addEdge(d, g);
		graph.addEdge(c,e);
		
		graph.analyze();
		graph.analyze();
		MyHashTable<String, Integer> hash = new MyHashTable<String, Integer>();
		
		hash.put("abc", 1);
		hash.put("abc", 2);
		System.out.println(hash.get("abc"));
		
	}
}
