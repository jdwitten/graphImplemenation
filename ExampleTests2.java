package a4;

/**
 * Some examples of tests. You'll want to write more tests.
 */
public class ExampleTests2 {

	private static interface Animal {
		public String speak();
	}
	
	
	public static class Fish implements Animal{
		String name;
		public Fish(String name){
			this.name = name;
		}
		
		public int hashCode(){
			return 435;
		}

		@Override
		public String speak() {
			return name;
		}
	}

	
	private static class Dog implements Animal {
		@Override
		public String speak() {
			return "woof";
		}
	}

	private static class Cat implements Animal {
		private static int count = 0;
		@Override
		public String speak() {
			return "meow "+ count++;
		}
	}

	
	public static void main(String[] args) {
		int errorCount = 0;
		boolean printErrorMessage = false;

		HashTable<String, Animal> animals = HashTableFactory.create();
		HashTable<Fish, Fish> fishes = HashTableFactory.create(); 
		Fish fishOne = new Fish("terry");
		Fish fishTwo = new Fish("penny");
		Fish fishThree = new Fish("fran");
		// Hash table
		fishes.put(fishOne, fishOne);
		fishes.put(fishTwo, fishTwo);
		fishes.put(fishThree, fishThree);
		
		animals.put("dog", new Dog());
		
		for(int i = 0; i < 999; i++){
			animals.put("cat " + i, new Cat());
		}
		
		
		if(!"terry".equals(fishes.get(fishOne).speak())) printErrorMessage = true;
		if(!"penny".equals(fishes.get(fishTwo).speak())) printErrorMessage = true;
		if(!"fran".equals(fishes.get(fishThree).speak())) printErrorMessage = true;
		System.out.println(printErrorMessage? "problem getting object hashed to same location " + "Error:" + errorCount++: "Nicely done,"
				+ "everything looks good when getting an Object hashed to the same location.");
		printErrorMessage = false;
		
		if(!fishes.remove(fishOne).speak().equals("terry")) printErrorMessage = true;
		if(fishes.get(fishOne) != null) printErrorMessage = true;
		if(!"penny".equals(fishes.get(fishTwo).speak())) printErrorMessage = true;
		if(!"fran".equals(fishes.get(fishThree).speak())) printErrorMessage = true;
		System.out.println(printErrorMessage? "problem removing first object from location with mulitple objects " + "Error:" + errorCount++: 
				 "everything looks dandy removing first object from location with multiple objects");
		printErrorMessage = false;
		
		fishes.put(fishOne, fishOne);
		if(!fishes.remove(fishThree).speak().equals("fran")) printErrorMessage = true;
		if(!"penny".equals(fishes.get(fishTwo).speak())) printErrorMessage = true;
		if(!"terry".equals(fishes.get(fishOne).speak())) printErrorMessage = true;
		if(fishes.get(fishThree) != null) printErrorMessage = true;
		System.out.println(printErrorMessage? "problem removing middle object from location with mulitple objects" + "Error:" + errorCount++:
				"everything looks peachy removing middle object from location with multiple objects");
		printErrorMessage = false;
		
		fishes.put(fishThree, fishThree);
		if(!fishes.remove(fishThree).speak().equals("fran")) printErrorMessage = true;
		if(!"penny".equals(fishes.get(fishTwo).speak())) printErrorMessage = true;
		if(!"terry".equals(fishes.get(fishOne).speak())) printErrorMessage = true;
		if(fishes.get(fishThree) != null) printErrorMessage = true;
		System.out.println(printErrorMessage? "problem removing last object from location with mulitple objects " + "Error:" + errorCount++:
				 "everything looks great removing last object from location with multiple objects");
		printErrorMessage = false;
		
		if(!"woof".equals(animals.get("dog").speak())) printErrorMessage = true;
		for(int i = 0; i < 999; i++){
			if(!("meow " + i).equals(animals.get("cat "+ i).speak())) printErrorMessage = true;
		}
		System.out.println(printErrorMessage? "Problem with putting or getting multiple objects":
				 "everything looks beautiful when putting and getting multiple objects");
		printErrorMessage = false;

		// Graph building
		Graph graph = GraphFactory.create(1000);

		for(int i = 0; i < 1000; i++){
			graph.addNode(NodeFactory.create("node " + i));
		}
		
		int[] ids = new int[1000];
		for(int i = 0; i < 1000; i++){
			ids[i] = graph.lookupNode("node " + i).getId();
			for(int j = 0; j < i;j++){
				if(ids[i] == ids[j]) printErrorMessage = true;
			}
		}
		System.out.println(printErrorMessage? "Problem adding 1000 nodes to graph or getting unique ids":
				 "everything looks nice, added 1000 nodes and they all have unique ids");
		printErrorMessage = false;
		
		for(int i = 0; i < 999; i++){
			graph.addEdge(graph.lookupNode("node " + i), graph.lookupNode("node "+ (i + 1)));
		}

		// Graph analysis
		System.out.println("Expecting an acyclic graph with sorted output: node 0 to node 999");
		graph.analyze();
		
		System.out.println("Expecting a cyclic graph.");
		graph.addEdge(graph.lookupNode("node 999"), graph.lookupNode("node 0"));
		graph.analyze();
		
		System.out.println(errorCount == 0? "Woot!! as long as there were no problems on graph.analyz() everthing looks good :)": "Testing reports "+ errorCount +" errors :(");
	}

}
