import java.util.*;

public class Rohit {
    public static void main(String[] args) {
        HashMap<String, Integer> hm= new HashMap<>();
        
        hm.put("Rohit", 20);
        hm.put("Nitin", 18);
        hm.put("dfkd", 45);
        hm.put("Nitsdfsdin", 25);
        hm.put("Ndfdfitin", 81);
        hm.put("Nidffdtin", 41);

        System.out.println(hm);

        HashSet<String> set = new HashSet<>();
        set.add("Rohit");
        set.add("Rahul");
        System.out.println(set);
    }
}
