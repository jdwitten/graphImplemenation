package a4;

public class Queue<T>{
	private QueueNode<T> lastIn;
	private QueueNode<T> firstOut;
	
	public Queue(){
		lastIn = null;
		firstOut = null;
	}
	
	//If both last and first items are null, the queue is empty
	public boolean isEmpty(){
		if(lastIn==null && firstOut==null){return true;}
		else{return false;}
	}
	
	//Gets the data from the next item in the queue
	public T peek(){
		if(isEmpty()){return null;}
		else{return firstOut.getData();}
		}
	
	//Checks if this was the last item in the queue, then makes
	//the second item now the first item
	public T dequeue(){
		if(isEmpty()){return null;}
		else{
			T dequeueData = firstOut.getData();
			if(firstOut.getNext()==null){firstOut = null; lastIn=null;}
			else{firstOut = firstOut.getNext();}
			return dequeueData;
		}
	}
	
	//Sets the memory of the 2nd to last item to know that it has something 
	//behind it in the queue, then adds the new item to the back
	public void enqueue(T element){
		if(isEmpty()){
			QueueNode<T> newitem = new QueueNode<T>(element, null);
			lastIn = newitem;
			firstOut = newitem;
		}
		else{
			QueueNode<T> newitem = new QueueNode<T>(element, null);
			lastIn.changeNext(newitem);
			lastIn = newitem;
		}
	}
	

}
