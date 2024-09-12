// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab5;

import java.io.IOException;
import java.io.Reader;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Lab5 {
  /**
   * Computes the maximum flow for a flow network.
   * @param g a graph with edge capacities and a source and sink
   * @return shortest distance between start and end
   */
  public static int maxFlow(FlowGraph g, int source, int sink) {
    // TODO(D2): Impelementera Edmonds-Karp varianten av Ford-Fulkerson algoritmen.
    // Det vill säga, använd bredden först-sökning för att hitta en väg med positivt flöde,
    // subtrahera det flödet och upprepa tills det inte går att skicka igenom mer flöden.
    int flow = 0;
    int bottleNeck = Integer.MAX_VALUE;
    //int smallest = Integer.MAX_VALUE;
    int[][] residual = new int [g.vertexCount()] [g.vertexCount()];
    int[] pred = new int [g.vertexCount()];
    for(int i=0; i<g.vertexCount(); i++) {
      for(int j=0; j<g.vertexCount(); j++) {
        residual[i][j] = g.getCapacity(i, j);
      }
    }

    while(true) {
      if(!bfs(g.vertexCount(), source, sink, residual, pred)) {
        break;
      }
      int cur = sink;
      int pre = pred[sink];

      while(cur != pre) {
        if(residual[pre][cur] < bottleNeck) {
          bottleNeck = residual[pre][cur];
        }
        cur = pre;
        pre = pred[pre];
      }
      cur = sink;
      pre = pred[sink];

      flow += bottleNeck;
      residual[pre][cur] -= bottleNeck;
      residual[cur][pre] += bottleNeck;
      while(cur != pre) {                                                           
        cur = pre;
        pre = pred[pre];
        residual[pre][cur] -= bottleNeck;
        residual[cur][pre] += bottleNeck;
      }
    }
    return flow;
  }

  private static boolean bfs(int numVertex, int start, int end, int[][] residual, int[] pred ) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    Set<Integer> visited = new HashSet<>();
    queue.add(start);
    visited.add(start);

    while(!queue.isEmpty()) {
      Integer latest = queue.poll();

      if(latest == end) {
        return true;
      }

      for(int i=0; i < numVertex; i++) {
        if(!visited.contains(i) && residual[latest][i] > 0) {
          pred[i] = latest;
          /*if(residual[latest][i] < smallest) {
            smallest = residual[latest][i];
          } */
          queue.add(i);
          visited.add(i);
        }
      }
    }
    return false;
  }

  /**
   * Read a flowgraph from a file.
   */
  public static FlowGraph loadFlowGraph(Path path) throws IOException {
    // TODO(D3): läs in ett flödesnätverk från fil.
    // Filen börjar med ett heltal som anger antalet noder,
    // sedan följer ett tal m som är antalet bågar.
    // Resten av filen består av m rader där varje rad anger en båge i
    // formatet u v c som beskriver en båge från en nod u till v med kapacitet c.
    Scanner scan = new Scanner(path);
    int vertexnmbr = scan.nextInt();
    int nmbrBows = scan.nextInt();
    FlowEdge[] temp = new FlowEdge[nmbrBows];
    int counter = 0;
    while(scan.hasNextInt()) {
      int source = scan.nextInt();
      int destination = scan.nextInt();
      int maxCap = scan.nextInt();
      if(maxCap == -1){
        maxCap = Integer.MAX_VALUE;
      }
      temp[counter] = new FlowEdge(source, destination, maxCap);
      counter++;
    }
    return new FlowGraph(vertexnmbr, temp);
  }
}
