package a4;

public class Chain<T,K> {
	private Link<T,K> firstLink;
	public Link<T,K> next;
	
	public Chain(Link<T,K> first){
		firstLink = first;
		next = null;
	}
	
	public Link<T,K> getFirst(){
		return firstLink;
	}
	
	public void insert(Link<T,K> a){
		Link<T,K> current = firstLink;
		while(current!=null){
			if(current.getKey().equals(a.getKey())&&!current.checklazyDelete()){
				current.changeData(a.getData());
				return;
			}
			if(current.getNext()==null){current.changeNext(a);}
			else{current=current.getNext();}
		}
	}
	
	public Link<T,K> find(K a){
		Link<T,K> current = firstLink;
		while(current!=null){
			if(a.equals(current.getKey())&&!current.checklazyDelete()){return current;}
			current=current.getNext();
		}
		return null;
	}
	
	public Link<T,K> containsKey(K a){
		Link<T,K> current = firstLink;
		while(current!=null){
			if(a.equals(current.getKey())&&!current.checklazyDelete()){return current;}
			current=current.getNext();
		}
		return null;
	}

}
