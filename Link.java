package a4;

public class Link<T, K> {
	    private K key;
		private T data;
		private Link<T, K> nextLink;
		private boolean LazyDelete;
		
		public Link(T this_data,K this_key, Link<T, K> next){
			key = this_key;
			data = this_data;
			nextLink = next;
			LazyDelete=false;
		}
		public Link<T, K> getNext(){return nextLink;}
		
		public T getData(){return data;}
		
		public void changeNext(Link<T, K> newNext){
			nextLink = newNext;
			return;
		}
		public K getKey(){
			return key;
		}
		public boolean checklazyDelete(){
			return LazyDelete;
		}
		public void lazyDelete(){
			LazyDelete=true;
		}
		
		public void changeData(T newData){
			data=newData;
		}
	}
