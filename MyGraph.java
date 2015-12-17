package a4;

public class MyGraph extends Graph {
	public static int numberNodes;
	private int size;
	private LinkedList[] nodes;
	MyHashTable<String, MyNode> nodeLookup;
	private int[] indegreeChart;
	private int numNodesInGraph;
	
	public MyGraph(int a){
		numberNodes = 0;
		size=a;
		nodes = new LinkedList[size];
		nodeLookup = new MyHashTable<String, MyNode>();
		indegreeChart = new int[size];
		numNodesInGraph=0;
	}
	
	@Override
	public void addNode(Node node){
		nodes[node.getId()] = new LinkedList((MyNode) node);
		nodeLookup.put(node.getName(), (MyNode) node); 
		indegreeChart[node.getId()]++;
		numNodesInGraph++;
	}
	
	@Override
	public void addEdge(Node node1, Node node2){
		MyNode Bnode = new MyNode(node2.getName(), node2.getId());
		nodes[node1.getId()].insert(Bnode);
		indegreeChart[node2.getId()]++;
	}
	
	@Override
	public Node lookupNode(int Id){
		if(nodes[Id]==null){return null;}
		else{return nodes[Id].getFirst();}
	}
	
	@Override
	public Node lookupNode(String name){
		return nodeLookup.get(name);
	}
	
	@Override
	//Topologically Sorts the Graph and returns an array of Nodes in the correct order
	public int[] sort(){
		int[] result = new int[numNodesInGraph];
		//Make sure the graph is acyclic
		if(!isAcyclic()){return null;}
		
		Queue<MyNode> process = new Queue<MyNode>();
		int counter = -1;
		
		//Make a copy of the indegree chart
		int[] indegreeChartCopy = new int[size];
		for(int i=0; i<size;i++){
			indegreeChartCopy[i]=indegreeChart[i];
		}
		
		//Enqueue all nodes whose indegrees are 0
		for(int i=0; i<size; i++){
			if(indegreeChartCopy[i]==1){process.enqueue(nodes[i].getFirst());}
		}
		
		while(!process.isEmpty()){
			//The next item in the sort is the item being dequeued
			MyNode n = process.dequeue();
			counter++;
			result[counter]=n.getId();
			
			//Decremment the indegree of all nodes adjacent to the node being dequeued
			MyNode current=nodes[n.getId()].getFirst().nextNode;
			while(current!=null){
				indegreeChartCopy[current.getId()]--;
				
				//If their new indegree=0 then enqueue this node
				if(indegreeChartCopy[current.getId()]==1){
					process.enqueue(nodes[current.getId()].getFirst());
				}
				current=current.nextNode;
			}
		}
		return result;
	}
	
    public boolean isAcyclic(){
    	//Go through each node to test for a cycle
    	for(int i=0;i<numberNodes-1;i++){
    		if(nodes[i]==null){continue;}
    		//Establish the primary node at this position as the origin and enqueue it
    		MyNode origin = nodes[i].getFirst();
    		Queue<MyNode> process = new Queue<MyNode>();
    		process.enqueue(origin);
    		
    		//Go through every node adjacent to the origin and enqueue it
    		MyNode originNodes = nodes[origin.getId()].getFirst().nextNode;
			while(originNodes!=null){
				process.enqueue(originNodes);
				originNodes = originNodes.nextNode;
			}
			process.dequeue();
    		while(!process.isEmpty()){
    			//Check to see if the item dequeued is equal to the origin
    			//If so, then then graph has a cycle
    			//If not, enqueue the nodes adjacent to the last dequeued element
    			MyNode current = (MyNode) process.dequeue();
    			if(current.getId()==origin.getId()){return false;}
    			else{
    				MyNode next = nodes[current.getId()].getFirst().nextNode;
    				while(next!=null){
    					process.enqueue(next);
    					next = next.nextNode;
    				}
    			}
 
    		}
    	}
    	//If the algorithm gets to this point then no nodes have produced a cycle
    	return true;
    }
}
