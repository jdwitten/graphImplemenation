package a4;

public class QueueNode<T> {
		private T data;
		private QueueNode<T> nextNode;
		
		public QueueNode(T this_data, QueueNode<T> next){
			data = this_data;
			nextNode = next;
		}
		public QueueNode<T> getNext(){return nextNode;}
		
		public T getData(){return data;}
		
		public void changeNext(QueueNode<T> newNext){
			nextNode = newNext;
			return;
		}
	}
