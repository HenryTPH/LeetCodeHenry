import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetermineIfTwoStringAreClose {
    /**
     * 2 strings are close if:
     * 1. Swap any 2 existing characters: abcde -> aecdb
     * 2. Transform every occurrence of one existing character into another existing character: aacabb -> bbcbaa
     * @param word1
     * @param word2
     * @return
     */
    public static boolean closeStrings(String word1, String word2){
        if(word1.length() != word2.length()) return false;
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for(char w: word1.toCharArray()){
            map1.put(w, map1.getOrDefault(w, 0) + 1);
        }
        for(char w: word2.toCharArray()){
            map2.put(w, map2.getOrDefault(w, 0) + 1);
        }
        if(map1.size() != map2.size()) return false;
        ArrayList<Integer> listVal = new ArrayList<>();
        ArrayList<Character> listKey = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry: map1.entrySet()){
            listKey.add(entry.getKey());
            listVal.add(entry.getValue());            
        }
        for(Map.Entry<Character, Integer> entry: map2.entrySet()){
            listKey.remove(entry.getKey());
            listVal.remove(entry.getValue());            
        }
        return listVal.size() == 0 && listKey.size() == 0;
    }
    public static void main(String[] args) {
        String s1 = "uau";
        String s2 = "ssx";
        System.out.println(closeStrings(s1, s2));
    }
}
