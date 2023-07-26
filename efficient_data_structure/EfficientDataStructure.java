package efficient_data_structure;


import java.util.Arrays;
import java.util.HashMap;
public class EfficientDataStructure{

    int SIZE;
    String[] arr;
    HashMap< String, Integer > map;
    int count;
    
    public EfficientDataStructure(int size){
        arr = new String[size];    
        map = new HashMap< String, Integer >();
        count = -1;
        SIZE = size;
    }
    
    private boolean insert(String str){
        count++;
        if(count >= SIZE) return false;
        arr[count] = str;
        map.put(str, count);
        return true;
    }
    
    private boolean delete(String str){
    	if(count == 0 & map.containsKey(str)){
    		arr[count] = null;
    		Arrays.fill(arr, null);  // OPTIONAL
    		map.remove(str);
    		count--;
    		return true;
    	}
        if(!map.containsKey(str) || count == -1) return false;
        int index = map.get(str);
        String replaceWith = arr[count];
        arr[index] = replaceWith;
        arr[count] = null; 
        count--;
        map.remove(str);
        map.put(replaceWith, index);
        return true;
    }
    
    private boolean delete(int index){
    	if(index >= map.size()) return false;
    	if(count == 0){
    		
    		map.remove(arr[index]);
    		arr[count] = null;
    		Arrays.fill(arr, null);   // OPTIONAL
    		count--;
    		return true;
    	}
        
        String elementToDelete = arr[index];
        String replaceWith = arr[count];
        arr[index] = replaceWith;
        arr[count] = null;
        count--;
        map.remove(elementToDelete);
        map.put(replaceWith, index);
        return true;
    }
    
    private boolean contains(String str){
        return map.containsKey(str);
    }
    
    private String getElementAtIndex(int index){
        return arr[index];
    }
    
    private String getRandom(){
        int index = rand(0, count);
        return arr[index];
    }
    private int size(){
        return map.size();
    }
    
    private void returnAll(){
    	for(int i = 0; i <=count; i++){
    		System.out.print(arr[i] + "  ");
    	}
    	System.out.println();
    	for(String str: map.keySet()){
    		System.out.print(str + " ");
    	}
    	System.out.println();
    }
    
    private int rand(int beg, int end){
        int range = end - beg + 1;
        return beg + (int)(Math.random() * range);
    }
     public static void main(String []args){
     EfficientDataStructure ds = new EfficientDataStructure(100);
     System.out.println("Array Size: " + ds.arr.length);
     System.out.println("Size now: " + ds.size());
     ds.insert("Hello");
     ds.insert("World");
     ds.returnAll();
     System.out.println("Size after 2 insertions: " + ds.size());
     System.out.println("Random Access: " + ds.getRandom() );
     System.out.println(ds.contains("IamNotThere"));
     System.out.println(ds.contains("Hello"));
     ds.delete("Hello");
     System.out.println("Size after deletion: " + ds.size());
     System.out.println(ds.getElementAtIndex(1));
     ds.returnAll();
     System.out.println(ds.delete(0));
     System.out.println("Size After Another Deletion: " + ds.size() + " Count: " + ds.count);
	ds.returnAll();
     }
     
}