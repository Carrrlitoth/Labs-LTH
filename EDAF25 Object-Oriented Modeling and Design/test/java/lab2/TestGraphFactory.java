// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab2;

import graph.Graph;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

public class TestGraphFactory {
  private static <T> boolean reachable(Graph<T> g, T u, T v) {
    Stack<T> stack = new Stack<>();
    Set<T> visited = new HashSet<>();
    stack.push(u);
    while (!stack.isEmpty()) {
      T w = stack.pop();
      if (!visited.add(w))
        continue;
      if (w.equals(v))
        return true;
      for (T x : g.neighbours(w)) {
        if (!visited.contains(x)) {
          stack.push(x);
        }
      }
    }
    return false;
  }

  private static <T> boolean connected(Graph<T> g) {
    for (T u : g.vertexSet()) {
      for (T v : g.vertexSet()) {
        if (!reachable(g, u, v))
          return false;
      }
    }
    return true;
  }

  @Test public void testConnected() {
    Graph<Integer> g = GraphFactory.buildConnected();
    assertThat(g.vertexCount()).isAtLeast(5);
    assertThat(connected(g)).isTrue();
  }

  @Test public void testDisconnected() {
    Graph<Integer> g = GraphFactory.buildDisconnected();
    assertThat(g.vertexCount()).isAtLeast(5);
    assertThat(connected(g)).isFalse();
  }
}
