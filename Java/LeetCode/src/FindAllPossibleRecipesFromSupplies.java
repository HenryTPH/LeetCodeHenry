import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
        for(int i=0;i<recipes.length;i++){
            String item=recipes[i];
            for(int j=0;j<ingredients.get(i).size();j++){
                List<String> g=map.getOrDefault(ingredients.get(i).get(j),new ArrayList<>());
                g.add(item);
                map.put(ingredients.get(i).get(j),g);
                indegree.put(item,indegree.getOrDefault(item,0)+1);
            }
        }
        
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
    public static void main(String[] args) {
        
    }
}
