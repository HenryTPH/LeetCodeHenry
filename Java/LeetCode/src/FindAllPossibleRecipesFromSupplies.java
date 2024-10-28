import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class FindAllPossibleRecipesFromSupplies {
    /*
     * You have inforamtion about n different recipes. You are given a string array recipes and a 2D string array ingredients.
     * The ith recipe has the name recipesp[i], and you can create it if you have all the needed ingredients from ingredietnesp[i].
     * Ingredients to a recipe may need to be created from other recipes (Ingredients[i] may contain sa string that is in recipes)
     * A string array of supplies containing all the ingredietns that you initially have. You have an infinite supply of all of them.
     * Return a list of all the recipes that you can create. Answer may be in any order
     */
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies){
        HashMap<String, List<String>> map = new HashMap<>();
        HashMap<String, Integer> indegree = new HashMap<>();
        for(int i = 0; i < recipes.length; i++){
            for(String ingredient: ingredients.get(i)){
                if(!map.containsKey(ingredient)){
                    map.put(ingredient, new ArrayList<>(Arrays.asList(recipes[i])));
                } else {
                    map.get(ingredient).add(recipes[i]);                    
                }
                indegree.put(recipes[i], indegree.getOrDefault(recipes[i], 0) + 1);
            }
        }
        // Another way to create the map
        //for(int i=0;i<recipes.length;i++){
        //     String item=recipes[i];
        //     for(int j=0;j<ingredients.get(i).size();j++){
        //         List<String> g=map.getOrDefault(ingredients.get(i).get(j),new ArrayList<>());
        //         g.add(item);
        //         map.put(ingredients.get(i).get(j),g);
        //         indegree.put(item,indegree.getOrDefault(item,0)+1);
        //     }
        // }
        
        List<String> result = new ArrayList<>();
        for(String str: supplies){
            dfs(map, indegree, str);
        }
        for(String s: indegree.keySet()){
            if(indegree.get(s) <= 0) result.add(s);
        }
        return result;
    }
    private static void dfs(HashMap<String, List<String>> map, HashMap<String, Integer> indegree, String str){
        for(String product: map.getOrDefault(str, new ArrayList<>())){
            indegree.put(product, indegree.getOrDefault(product, 0) - 1);
            if(indegree.getOrDefault(product, 0) <= 0) dfs(map, indegree, product);
        }
    }
    /*
     * Use TopologicalSorting for this problem
     */
    public List<String> findAllRecipesTopo(String[] recipes, List<List<String>> ingredients, String[] supplies){
        HashMap<String, List<String>> map = new HashMap<>();
        HashMap<String, Integer> indegree = new HashMap<>();
        List<String> result = new ArrayList<>();
        for(int i = 0; i < recipes.length; i++){
            for(String ingredient: ingredients.get(i)){
                if(!map.containsKey(ingredient)){
                    map.put(ingredient, new ArrayList<>(Arrays.asList(recipes[i])));
                } else {
                    map.get(ingredient).add(recipes[i]);                    
                }
                indegree.put(recipes[i], indegree.getOrDefault(recipes[i], 0) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for(String s: supplies) queue.add(s);
        while(!queue.isEmpty()){
            String supply = queue.poll();
            for(String product: map.getOrDefault(supply, new ArrayList<>())){
                indegree.put(product, indegree.getOrDefault(product, 0) - 1);
                if(indegree.getOrDefault(product, 0) <= 0){
                    queue.add(product);
                }
            }
        }
        for(String s: indegree.keySet()){
            if(indegree.getOrDefault(s, 0) <= 0) result.add(s);
        }
        return result;
    }
    /*
     * More efficient using Topological Sort
     */
    public List<String> findAllRecipesEfficent(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        HashSet<String> supply = new HashSet<>();
        HashMap<String, Integer> index = new HashMap<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s: supplies) supply.add(s);
        for(int i = 0; i < recipes.length; i++){
            index.put(recipes[i], i);
        }
        int[] indegree = new int[recipes.length];
        for(int i = 0; i < recipes.length; i++){
            for(String need: ingredients.get(i)){
                if(supply.contains(need)) continue;
                map.putIfAbsent(need, new ArrayList<>());
                map.get(need).add(recipes[i]);
                indegree[i]++;
            }
        }
        LinkedList<Integer> q = new LinkedList<>();
        for(int i = 0; i < recipes.length; i++){
            if(indegree[i] == 0) q.add(i);
        }
        List<String> cooked = new ArrayList<>();
        while(!q.isEmpty()){
            int i = q.poll();
            cooked.add(recipes[i]);
            if(!map.containsKey(recipes[i])){
                // if the map does not contain this recipe, this means
                // this recipe is not an ingredient for any other recipe
                // and no further processing is required
                continue;
            }
            for(String recipe: map.get(recipes[i])){
                if(--indegree[index.get(recipe)] == 0){
                    q.add(index.get(recipe));
                }
            }
        }
        return cooked;
    }
    public static void main(String[] args) {
        
    }
}
