import java.util.LinkedList;
import java.util.Queue;

public class Dota2Senate {
    /**
     * 
     * */
    public static String predictPartyVictory(String senate){
        
        Queue<Integer> banD = new LinkedList<>();
        Queue<Integer> banR = new LinkedList<>();
        
        return predictHelper(senate, banD, banR);
    }
    private static String predictHelper(String s, Queue<Integer> banD,  Queue<Integer> banR){
        if(s.matches("R*")) return "Radiant";
        if(s.matches("D*")) return "Dire";
        StringBuilder sb = new StringBuilder(); 
        for(char c: s.toCharArray()){
            if(c == 'R' && banR.isEmpty()){
                banD.add(1);
                sb.append(c);
                continue;
            }
            if(c == 'D' && banD.isEmpty()){
                banR.add(1);
                sb.append(c);
                continue;
            }
            if(c == 'R' && !banR.isEmpty()){
                banR.poll();
                continue;
            }
            if(c == 'D' && !banD.isEmpty()){
                banD.poll();
                continue;
            }
        }
        return predictHelper(sb.toString(), banD, banR);
    }
    public static String predictPartyVictorySimpleApproach(String senate){
        Queue<Integer> rad = new LinkedList<>();
        Queue<Integer> dir = new LinkedList<>();
        int n = senate.length();
        for(int i = 0; i < senate.length(); i++){
            if(senate.charAt(i) == 'R') rad.add(i);
            if(senate.charAt(i) == 'D') rad.add(i);
        }
        while(!rad.isEmpty() && !dir.isEmpty()){
            if(rad.peek() < dir.peek()){
                rad.add(n++);
            } else {
                dir.add(n++);
            }
            rad.poll();
            dir.poll();
        }return rad.isEmpty() ? "Dire" : "Radiant";
    }
    public static void main(String[] args) {
        System.out.println(predictPartyVictory("RRRDD"));
    }
}
