import java.util.LinkedList;
import java.util.Queue;

public class NumberOfRecentCalls {
    class RecentCounter{
        Queue<Integer> recentRequest;
        public RecentCounter(){
            this.recentRequest = new LinkedList<>();
        }
        public int ping(int t){
            this.recentRequest.add(t);
            int leftRange = t - 3000;
            while(!this.recentRequest.isEmpty()){
                if(this.recentRequest.peek() < leftRange){
                    this.recentRequest.poll();
                }
            }
            return this.recentRequest.size();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
