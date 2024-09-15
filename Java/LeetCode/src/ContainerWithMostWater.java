public class ContainerWithMostWater {
    /**
     * Array of integer height of length n
     * n vertical lines such that the two endpoints of ith line are (i, 0) and (i, height[i])
     * Find 2 lines that together with the x-axis form a container, such that the container contains the most water.
     * Return the maximum amount of water a container can store
     * Not slant the container
     * @param height
     * @return
     */
    public static int maxArea(int[] height){
        int result = Integer.MIN_VALUE;
        int endpointer = height.length - 1;
        int startpointer = 0;
        while(startpointer < endpointer){
            int diff = endpointer - startpointer;
            int max = Integer.min(height[startpointer], height[endpointer]);
            int comp = max * diff;
            if(comp > result){
                result = comp;
            }
            if(height[startpointer] < height[endpointer]){
                startpointer++;
            } else {
                endpointer--;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }
}
