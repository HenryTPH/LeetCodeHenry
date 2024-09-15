public class FindTheHighestAltitude {
    /**
     * Prefix Sum:
     * The road trip consists of n+1 points at different altitudes
     * The biker starts on point 0 with altitude equal 0
     * gain[i] is the net gain in altitude between points i and i+1. Return the highest altitude of a point
     * @param gain
     * @return
     */
    public int largestAltitude(int[] gain){
        int max = 0;
        int sum = 0;
        for(int i = 0; i < gain.length; i++){
            sum += gain[i];
            max = Math.max(max, sum);
        }
        return max;
    }
    public static void main(String[] args) {
        
    }
}
