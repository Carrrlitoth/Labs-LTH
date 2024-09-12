// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab1;

import graph.Graph;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestBuildGraph {
  @Test public void test1() {
    Graph<Integer> g = Lab1.buildGraph();
    assertThat(g.vertexCount()).isAtLeast(6);
    assertThat(Lab1.edgeCount(g)).isAtLeast(10);
  }
}
