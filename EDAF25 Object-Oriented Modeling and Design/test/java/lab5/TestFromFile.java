// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab5;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;

public class TestFromFile {

  /** Test a graph with infinite capacities. */
  @Test public void small() throws IOException {
    FlowGraph g = Lab5.loadFlowGraph(Paths.get("small.txt"));
    assertThat(Lab5.maxFlow(g, 0, g.vertexCount()-1)).isEqualTo(30);
  }

  /** Test a graph with infinite capacities. */
  @Test public void sixnodes() throws IOException {
    FlowGraph g = Lab5.loadFlowGraph(Paths.get("sixnodes.txt"));
    assertThat(Lab5.maxFlow(g, 0, g.vertexCount()-1)).isEqualTo(18);
  }

  /** Test a graph with infinite capacities. */
  @Test public void rail() throws IOException {
    FlowGraph g = Lab5.loadFlowGraph(Paths.get("rail.txt"));
    assertThat(Lab5.maxFlow(g, 0, g.vertexCount()-1)).isEqualTo(146);
  }

}
