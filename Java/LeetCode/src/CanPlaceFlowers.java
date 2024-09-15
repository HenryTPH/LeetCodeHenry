public class CanPlaceFlowers {
    /**
     * A long flowerbed in which some of the plots are planted, and some are not.
     * Flowers cannot be planted in adjacent plots
     * flowerbed: array of 0 - empty and 1 - not empty
     * n: integer
     * Return: boolean
     *  -> n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise
     * 
     * Ex: [1, 0, 0, 0, 1], n=1 -> true; n=2 -> false
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n){
        int count = 0;
        if(flowerbed.length == 1 && flowerbed[0] == 0) count++;
        for(int i = 0; i < flowerbed.length-2; i++){
            if(flowerbed[0] == 0 && flowerbed[1] == 0){
                flowerbed[0] = 1;
                count++;
            }
            if(flowerbed[flowerbed.length-2] == 0 && flowerbed[flowerbed.length-1] == 0){
                flowerbed[flowerbed.length-1] = 1;
                count++;
            }
            if (flowerbed[i] == 0 && flowerbed[i+1] == 0 && flowerbed[i+2] == 0){
                flowerbed[i+1] = 1;
                count++;
            }
        }
        return count>=n;
    }

    public static boolean canPlaceflowers2(int[] flowerbed, int n) {
        if(n == 0){
            return true;
        }
        for(int i = 0; i < flowerbed.length; i++){
            if(flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)){
                flowerbed[i] = 1;
                n--;
                if(n == 0) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 0, 0};
        System.out.println(canPlaceflowers2(arr, 3));
    }
}
