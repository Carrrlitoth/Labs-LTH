package textproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.io.FileNotFoundException;

public class GeneralWordCounter implements TextProcessor {
    private Set s;
    private  Map<String, Integer> m;
    public GeneralWordCounter(Set s) throws FileNotFoundException{
        m = new TreeMap<>();
        this.s = s;
    }

    @Override
    public void process(String w) {
        if(!s.contains(w)) {
            if(m.containsKey(w)) {
                m.put(w, m.get(w)+1);
            }
            else {
                m.put(w, 1);
            }
        }
        
    }

    @Override
    public void report() {
        Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);

        wordList.sort((w1, w2) -> {
            if(w1.getValue().equals(w2.getValue())) {
                return w1.getKey().compareTo(w2.getKey());}
            else {
            return w2.getValue() - w1.getValue();}});

        for(int i=0; i<5; i++) {
            System.out.println(wordList.get(i).getKey() + " " + wordList.get(i).getValue());
        }

        // for(String key : m.keySet()){
        //     if(m.get(key) >= 200){
        //         System.out.println(key + " " + m.get(key));
        //     }
        // }
    }

    public List<Map.Entry<String, Integer>> getWordList() {
        Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        return wordList;
    }
    
}
