// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/** Helper class for the priority queue in Dijkstras algorithm. */
class PQElement {
  int node;
  int distance;

  public PQElement(int node, int dist) {
    this.node = node;
    this.distance = dist;
  }
}

public class Lab4 {
  /**
   * Computes the shortest distance between start and end in the graph g using Dijkstra's
   * algorithm.
   * This version handles only graphs with integer edge distances.
   * @param g a graph with distance information attached to the edges
   * @param start start vertex
   * @param end end vertex
   * @return shortest distance between start and end
   */
  public static int distance(DistanceGraph g, int start, int end) {
    // Comparator för att hålla kön sorterad på avstånd:
    // TODO(D2): slutför Dijkstras algoritm för att hitta kortaste avstånd start->end.
    Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
    PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
    HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
    queue.add(new PQElement(start, 0));
    visited.put(start, 0);

    while(!queue.isEmpty()){
      PQElement smallest = queue.poll();

      if(smallest.node == end) return smallest.distance;

      for(Edge edge : g.edges(smallest.node)){
        int w = edge.destination;
        int newDist = smallest.distance + edge.distance;

        if(!visited.containsKey(w) || newDist < visited.get(w)){
          visited.put(w, newDist);

          boolean nodeAlreadyExists = false;

          for(PQElement element : queue){
            if(element.node == w){
              nodeAlreadyExists = true;
              if(newDist < element.distance){
                queue.remove(element);
                queue.add(new PQElement(w, newDist));
              }
              break;
            }
          }
          if(!nodeAlreadyExists){
            queue.add(new PQElement(w, newDist));
          }
        }
      }

    }

    return -1;
  }

  /**
   * Finds a shortest path between start and end in a graph g Dijkstra's
   * algorithm.
   * The graph can have any distance unit.
   * @param g a graph with distance information attached to the edges
   * @param start start vertex
   * @param end end vertex
   * @return a list containing the nodes on the shortest path from start to end
   */
  public static List<Integer> shortestPath(DistanceGraph g, int start, int end) {
    Comparator<PQElement> cmp = Comparator.comparingInt(e -> e.distance);
    PriorityQueue<PQElement> queue = new PriorityQueue<>(cmp);
    HashMap<Integer, Integer> visited = new HashMap<>();
    HashMap<Integer, Integer> prev = new HashMap<>();
    LinkedList<Integer> answer = new LinkedList<>();

    queue.add(new PQElement(start, 0));
    visited.put(start, 0);

    while (!queue.isEmpty()) {
      PQElement smallest = queue.poll();

      if (smallest.node == end) {
        break;
      }

      for (Edge edge : g.edges(smallest.node)) {
        int w = edge.destination;
        int newDist = smallest.distance + edge.distance;
        int wDist = visited.getOrDefault(w, Integer.MAX_VALUE);

        if (newDist < wDist) {
          visited.put(w, newDist);
          prev.put(w, smallest.node);
          queue.add(new PQElement(w, newDist));
        }
      }

    }

    int node = end;
    while (node != start) {
      answer.addFirst(node);
      node = prev.get(node);
    }

    answer.addFirst(start);
    return answer;
  }
}

