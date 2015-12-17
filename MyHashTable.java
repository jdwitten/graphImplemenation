package a4;

public class MyHashTable<K, V> implements HashTable<K, V>{
	
	private Object[] table;
	private int tableSize;
	
	public MyHashTable(){
		tableSize = 1009;
		table = new Object[tableSize];
	}
	
	 /**
     * Associates the specified value with the specified key in this hash table.
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void put(K key, V value){
    	//Find the location where the corresponding key maps to
    	int hashValue = Math.abs(key.hashCode()%tableSize);
    	
    	//If there is nothing at this location, create a new linked list
    	if(table[hashValue]==null){
    		Link<V,K> link = new Link<V,K>(value, key, null);
    		Chain<V,K> chain = new Chain<V,K>(link);
    		table[hashValue]= chain;
    	}
    	//If there is already a linked list insert this value into it
    	else{
    		Chain<V,K> node = (Chain<V,K>) table[hashValue]; 
    		node.insert(new Link<V,K>(value,key,null));
    	}
    }
    
    /**
     * Returns the value to which the specified key is mapped.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if not found
     */
    public V get(K key){
    	//Find the location where the corresponding key maps to
    	int hashValue = Math.abs(key.hashCode()%tableSize);
    	
    	//Check if there is anything at this position
    	if(table[hashValue]==null){return null;}
    	
    	//Check if the corresponding key is in the HashTable and return the value
    	Chain<V,K> node = (Chain<V,K>) table[hashValue]; 
    	Link<V,K> result = node.containsKey(key);
    	if(result==null){return null;}
    	else{return result.getData();}
    }
    
    /**
     * Returns the value to which the specified key is mapped and removes the entry from the table.
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if not found
     */
    public V remove(K key){
    	//find the location that the key maps to
    	int hashValue = Math.abs(key.hashCode()%tableSize);
    	
    	//Check if there is a node at this position
    	if(table[hashValue]==null){return null;}
    	
    	//Check if the node with the corresponding key is in this LinkedList
    	Chain<V,K> node = (Chain<V,K>) table[hashValue]; 
    	Link<V,K> result = node.find(key);
    	if(result==null){return null;}
    	else{
    		result.lazyDelete();
    		return result.getData();
    	}
    }


	
	
	
	
}
