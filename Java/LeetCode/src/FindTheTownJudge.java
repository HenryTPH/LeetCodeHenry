import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;

public class FindTheTownJudge {
    /**
     * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

 
     * @param n
     * @param trust
     * @return
     */
    public int findJudge(int n, int[][] trust){
        HashMap<Integer, HashSet<Integer>> trustedBy = new HashMap<>();
        for(int i = 1; i <= n; i++){
            trustedBy.put(i, new HashSet<>());
        }
        for(int i = 0; i < trust.length; i++){
            trustedBy.get(trust[i][1]).add(trust[i][0]);
        }
        int potentialJudge = -1;
        for(Map.Entry<Integer, HashSet<Integer>> entry: trustedBy.entrySet()){
            if(entry.getValue().size() == n-1){
                potentialJudge = entry.getKey();
            }
        }
        for(Map.Entry<Integer, HashSet<Integer>> entry: trustedBy.entrySet()){
            if(entry.getKey() == potentialJudge) continue;
            if(entry.getValue().contains(potentialJudge)) return -1;
        }           
        return potentialJudge;
    }
    public static void main(String[] args) {
        
    }
}
