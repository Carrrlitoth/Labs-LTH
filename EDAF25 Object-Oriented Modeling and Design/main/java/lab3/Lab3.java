// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab3;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Lab3 {
  public static <T> int distance(Graph<T> g, T source, T dest) {
    Map<T, Integer> distance = new HashMap<>();
    Queue<T> queue = new LinkedList<>();

    distance.put(source, 0);
    queue.add(source);

    if (source.equals(dest)) {
      return 0;
    }
    while (!queue.isEmpty()) {
      T vertex = queue.poll();
      int dist = distance.get(vertex);
      for (T neighbor : g.neighbours(vertex)) {
        if (!distance.containsKey(neighbor)) {
          distance.put(neighbor, dist + 1);
          queue.add(neighbor);
          if (neighbor.equals(dest)) {
            return dist + 1;
          }
        }
      }
    }
    return -1;
  }

}
