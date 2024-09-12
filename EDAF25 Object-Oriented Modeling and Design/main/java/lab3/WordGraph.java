// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab3;

import graph.Graph;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordGraph implements Graph<String> {
  private final Map<String, Set<String>> graph = new HashMap<>();
  private OneLetterDiff diff = new OneLetterDiff();

  public WordGraph(Path wordfile, WordCriteria criteria) throws IOException {
     // TODO(D1): load all words from file (wordfile).
     Reader in = Files.newBufferedReader(wordfile);
     Scanner scan = new Scanner(in);
 
     while (scan.hasNext()) {
       String word = scan.nextLine();
       graph.put(word, null);
     }
     // TODO(D3): compute word neighbours (according to criteria).
     for (String s : graph.keySet()) {
       Set<String> neighbors = new HashSet<>();
       for (String s2 : graph.keySet()) {
         if (diff.adjacent(s, s2))
           neighbors.add(s2);
       }
       graph.put(s, neighbors);
     }
  }

  @Override public int vertexCount() {
    // TODO(D1): implement this!
    return graph.size();
  }

  @Override public Collection<String> vertexSet() {
    // TODO(D1): implement this!
    Collection<String> temp = new HashSet<String>();

    for (Map.Entry<String, Set<String>> g : graph.entrySet()) {
      temp.add(g.getKey());
    }
    return temp;
  }

  @Override public Collection<String> neighbours(String v) {
    return graph.getOrDefault(v, Collections.emptySet());
  }
}
