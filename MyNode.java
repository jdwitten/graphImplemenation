package a4;

public class MyNode implements Node{
	private String name;
	private int id;
	public MyNode nextNode;

	public MyNode(String a, int IDflag){
		if(IDflag==-1){
			id = MyGraph.numberNodes;
			MyGraph.numberNodes ++;
		}
		else{
			id=IDflag;
		}
		name = a;
		nextNode=null;

	}
	public int getId(){return id;}
	
	public String getName(){return name;}
	
}
