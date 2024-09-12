// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab4;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Tests for Dijkstra's algorithm in graphs with integer distances.
 */
public class TestDistance {

  /** If there is no path from u to v, distance returns -1. */
  @Test public void noPath() {
    DistanceGraph g = new DistanceGraph(2);
    assertThat(Lab4.distance(g, 0, 1)).isEqualTo(-1);
  }

  @Test public void dag1() {
    DistanceGraph g = new DistanceGraph(2,
        new Edge(0, 1, 4)
    );
    assertPathLength(g, 0, 0, 0);
  }

  @Test public void dag2() {
    DistanceGraph g = new DistanceGraph(2,
        new Edge(0, 1, 7)
    );
    assertPathLength(g, 0, 1, 7);
  }

  @Test public void dag3() {
    DistanceGraph g = new DistanceGraph(3,
        new Edge(0, 1, 7),
        new Edge(0, 2, 5),
        new Edge(2, 1, 4)
    );
    assertPathLength(g, 0, 1, 7);
  }

  @Test public void dag4() {
    DistanceGraph g = new DistanceGraph(3,
        new Edge(0, 1, 7),
        new Edge(0, 2, 5),
        new Edge(2, 1, 1)
    );
    assertPathLength(g, 0, 1, 6);
  }

  @Test public void cycle1() {
    DistanceGraph g = new DistanceGraph(3,
        new Edge(0, 1, 4), new Edge(1, 0, 4),
        new Edge(1, 2, 3), new Edge(2, 1, 3),
        new Edge(2, 0, 13), new Edge(0, 2, 13)
    );
    assertPathLength(g, 0, 2, 7);
  }

  @Test public void cycle2() {
    DistanceGraph g = new DistanceGraph(3,
        new Edge(0, 1, 4), new Edge(1, 0, 4),
        new Edge(1, 2, 3), new Edge(2, 1, 3),
        new Edge(2, 0, 6), new Edge(0, 2, 6)
    );
    assertPathLength(g, 0, 2, 6);
  }

  @Test public void graph1() {
    DistanceGraph g = new DistanceGraph(4,
        new Edge(0, 1, 8),
        new Edge(0, 2, 2),
        new Edge(2, 1, 4),
        new Edge(2, 3, 1),
        new Edge(3, 1, 1)
    );
    assertPathLength(g, 0, 1, 4);
  }

  @Test public void graph2() {
    DistanceGraph g = new DistanceGraph(8,
        new Edge(0, 1, 5),
        new Edge(1, 0, 5),
        new Edge(0, 2, 9),
        new Edge(2, 0, 9),
        new Edge(0, 4, 1),
        new Edge(4, 0, 1),
        new Edge(1, 3, 1),
        new Edge(3, 1, 1),
        new Edge(1, 5, 2),
        new Edge(5, 1, 2),
        new Edge(1, 6, 7),
        new Edge(6, 1, 7),
        new Edge(1, 7, 4),
        new Edge(7, 1, 4),
        new Edge(2, 3, 6),
        new Edge(3, 2, 6),
        new Edge(2, 6, 2),
        new Edge(6, 2, 2),
        new Edge(3, 5, 3),
        new Edge(5, 3, 3),
        new Edge(3, 7, 3),
        new Edge(7, 3, 3),
        new Edge(4, 6, 1),
        new Edge(6, 4, 1)
    );
    assertPathLength(g, 0, 4, 1);
    assertPathLength(g, 0, 5, 7);
    assertPathLength(g, 0, 2, 4);
    assertPathLength(g, 2, 0, 4);
    assertPathLength(g, 2, 1, 7);
    assertPathLength(g, 2, 3, 6);
  }

  @Test public void graph3() {
    DistanceGraph g = new DistanceGraph(7,
        new Edge(0, 1, 5),
        new Edge(0, 2, 12),
        new Edge(0, 3, 20),
        new Edge(0, 5, 30),
        new Edge(0, 6, 40),
        new Edge(1, 3, 9),
        new Edge(1, 4, 7),
        new Edge(2, 4, 11),
        new Edge(2, 5, 5),
        new Edge(3, 6, 6),
        new Edge(4, 5, 3)
    );
    assertPathLength(g, 0, 2, 12);
    assertPathLength(g, 0, 5, 15);
    assertPathLength(g, 0, 6, 20);
  }

  @Test public void graph4() {
    DistanceGraph g = new DistanceGraph(7,
        new Edge(3, 1, 18),
        new Edge(3, 2, 26),
        new Edge(3, 5, 22),
        new Edge(3, 4, 27),
        new Edge(3, 6, 22),
        new Edge(5, 0, 23),
        new Edge(5, 3, 22),
        new Edge(5, 4, 28),
        new Edge(2, 3, 26),
        new Edge(2, 4, 15),
        new Edge(4, 1, 1),
        new Edge(4, 2, 15),
        new Edge(4, 3, 27),
        new Edge(4, 5, 28),
        new Edge(1, 0, 2),
        new Edge(1, 3, 18),
        new Edge(1, 6, 10),
        new Edge(1, 4, 1),
        new Edge(0, 1, 2),
        new Edge(0, 5, 23),
        new Edge(6, 1, 10),
        new Edge(6, 3, 22)
    );
    assertPathLength(g, 6, 3, 22);
    assertPathLength(g, 1, 2, 16);
    assertPathLength(g, 4, 3, 19);
    assertPathLength(g, 2, 1, 16);
    assertPathLength(g, 4, 1, 1);
    assertPathLength(g, 6, 5, 35);
    assertPathLength(g, 3, 5, 22);
    assertPathLength(g, 5, 0, 23);
    assertPathLength(g, 0, 1, 2);
    assertPathLength(g, 0, 6, 12);
  }

  @Test public void graph5() {
    DistanceGraph g = new DistanceGraph(10,
        new Edge(5, 2, 7),
        new Edge(5, 3, 14),
        new Edge(5, 7, 11),
        new Edge(6, 0, 27),
        new Edge(6, 1, 5),
        new Edge(6, 3, 19),
        new Edge(9, 2, 29),
        new Edge(9, 4, 16),
        new Edge(9, 7, 18),
        new Edge(7, 5, 11),
        new Edge(7, 8, 18),
        new Edge(7, 9, 18),
        new Edge(3, 0, 3),
        new Edge(3, 5, 14),
        new Edge(3, 6, 19),
        new Edge(3, 8, 10),
        new Edge(2, 0, 14),
        new Edge(2, 5, 7),
        new Edge(2, 9, 29),
        new Edge(0, 2, 14),
        new Edge(0, 8, 29),
        new Edge(0, 6, 27),
        new Edge(0, 3, 3),
        new Edge(1, 6, 5),
        new Edge(8, 0, 29),
        new Edge(8, 3, 10),
        new Edge(8, 4, 12),
        new Edge(8, 7, 18),
        new Edge(4, 9, 16),
        new Edge(4, 8, 12)
    );
    assertPathLength(g, 5, 3, 14);
    assertPathLength(g, 7, 8, 18);
    assertPathLength(g, 7, 5, 11);
    assertPathLength(g, 0, 2, 14);
    assertPathLength(g, 6, 8, 29);
    assertPathLength(g, 1, 8, 34);
    assertPathLength(g, 0, 2, 14);
    assertPathLength(g, 5, 0, 17);
    assertPathLength(g, 5, 9, 29);
    assertPathLength(g, 8, 0, 13);
  }

  @Test public void graph6() {
    DistanceGraph g = new DistanceGraph(10,
        new Edge(6, 2, 20),
        new Edge(6, 4, 2),
        new Edge(6, 5, 5),
        new Edge(6, 8, 25),
        new Edge(0, 2, 15),
        new Edge(2, 0, 15),
        new Edge(2, 1, 11),
        new Edge(2, 6, 20),
        new Edge(2, 7, 29),
        new Edge(2, 4, 12),
        new Edge(2, 5, 22),
        new Edge(9, 7, 26),
        new Edge(1, 8, 8),
        new Edge(1, 5, 8),
        new Edge(1, 7, 6),
        new Edge(1, 2, 11),
        new Edge(1, 3, 3),
        new Edge(5, 1, 8),
        new Edge(5, 2, 22),
        new Edge(5, 6, 5),
        new Edge(4, 2, 12),
        new Edge(4, 3, 28),
        new Edge(4, 6, 2),
        new Edge(8, 1, 8),
        new Edge(8, 6, 25),
        new Edge(7, 1, 6),
        new Edge(7, 2, 29),
        new Edge(7, 9, 26),
        new Edge(3, 1, 3),
        new Edge(3, 4, 28)
    );
    assertPathLength(g, 4, 2, 12);
    assertPathLength(g, 8, 5, 16);
    assertPathLength(g, 0, 6, 29);
    assertPathLength(g, 7, 9, 26);
    assertPathLength(g, 2, 6, 14);
    assertPathLength(g, 3, 7, 9);
    assertPathLength(g, 5, 6, 5);
    assertPathLength(g, 4, 0, 27);
    assertPathLength(g, 4, 9, 47);
    assertPathLength(g, 5, 3, 11);
  }

  /** Helper method to check that the shortest path from u to v in g has the expected length. */
  private static void assertPathLength(DistanceGraph g, int u, int v, int expected) {
    assertThat(Lab4.distance(g, u, v)).isEqualTo(expected);
  }
}
