package a4;

public class LinkedList {
	private MyNode firstNode;
	public MyNode next;
	
	public LinkedList(MyNode first){
		firstNode = first;
		next = null;
	}
	
	public MyNode getFirst(){
		return firstNode;
	}
	
	public void insert(MyNode a){
		MyNode current = firstNode;
		while(current.nextNode!=null){
			current=current.nextNode;
		}
		current.nextNode = a;
	}
	
	public boolean contains(MyNode a){
		MyNode current = firstNode;
		while(current!=null){
			if(a.getId()==current.getId()){return true;}
			current=current.nextNode;
		}
		return false;
	}

}
