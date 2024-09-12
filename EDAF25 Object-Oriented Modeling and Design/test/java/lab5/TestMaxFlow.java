// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab5;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestMaxFlow {
  @Test public void test1() {
    FlowGraph g = new FlowGraph(2, new FlowEdge(0, 1, 10));
    assertThat(Lab5.maxFlow(g, 0, 1)).isEqualTo(10);
  }

  @Test public void test2() {
    FlowGraph g = new FlowGraph(3,
        new FlowEdge(0, 1, 10),
        new FlowEdge(1, 2, 8)
    );
    assertThat(Lab5.maxFlow(g, 0, 2)).isEqualTo(8);
  }

  @Test public void test3() {
    FlowGraph g = new FlowGraph(5,
        new FlowEdge(0, 1, 1),
        new FlowEdge(0, 2, 2),
        new FlowEdge(0, 3, 4),
        new FlowEdge(1, 4, 8),
        new FlowEdge(2, 4, 8),
        new FlowEdge(3, 4, 8)
    );
    assertThat(Lab5.maxFlow(g, 0, 4)).isEqualTo(7);
  }

  @Test public void test4Infinite() {
    FlowGraph g = new FlowGraph(2,
        new FlowEdge(0, 1, Integer.MAX_VALUE)
    );
    assertThat(Lab5.maxFlow(g, 0, 1)).isEqualTo(Integer.MAX_VALUE);
  }

  @Test public void test5Infinite() {
    FlowGraph g = new FlowGraph(3,
        new FlowEdge(0, 1, Integer.MAX_VALUE),
        new FlowEdge(1, 2, 13)
    );
    assertThat(Lab5.maxFlow(g, 0, 2)).isEqualTo(13);
  }

  @Test public void test6() {
    FlowGraph g = new FlowGraph(6,
        new FlowEdge(0, 1, 4),
        new FlowEdge(0, 2, 4),
        new FlowEdge(2, 3, 4),
        new FlowEdge(1, 3, 4),
        new FlowEdge(1, 4, 4),
        new FlowEdge(4, 5, 4),
        new FlowEdge(3, 5, 4)
    );
    assertThat(Lab5.maxFlow(g, 0, 5)).isEqualTo(8);
  }

  @Test public void generated1() {
    FlowGraph g = new FlowGraph(10,
        new FlowEdge(5, 8, 25),
        new FlowEdge(5, 9, 66),
        new FlowEdge(5, 6, 39),
        new FlowEdge(5, 7, 26),
        new FlowEdge(1, 8, 78),
        new FlowEdge(1, 4, 72),
        new FlowEdge(1, 5, 24),
        new FlowEdge(1, 6, 29),
        new FlowEdge(8, 2, 64),
        new FlowEdge(8, 5, 30),
        new FlowEdge(8, 6, 47),
        new FlowEdge(8, 7, 1),
        new FlowEdge(4, 9, 1),
        new FlowEdge(4, 3, 55),
        new FlowEdge(4, 1, 28),
        new FlowEdge(2, 1, 14),
        new FlowEdge(2, 9, 12),
        new FlowEdge(6, 8, 39),
        new FlowEdge(6, 2, 57),
        new FlowEdge(6, 4, 5),
        new FlowEdge(6, 5, 79),
        new FlowEdge(0, 1, 55),
        new FlowEdge(0, 2, 17),
        new FlowEdge(0, 3, 76),
        new FlowEdge(0, 6, 56),
        new FlowEdge(3, 8, 6),
        new FlowEdge(3, 6, 25),
        new FlowEdge(3, 7, 49),
        new FlowEdge(7, 8, 2),
        new FlowEdge(7, 2, 60),
        new FlowEdge(7, 4, 68),
        new FlowEdge(7, 5, 2)
    );
    assertThat(Lab5.maxFlow(g, 0, 9)).isEqualTo(79);
  }

  @Test public void generated2() {
    FlowGraph g = new FlowGraph(10,
        new FlowEdge(2, 8, 29),
        new FlowEdge(2, 1, 49),
        new FlowEdge(2, 3, 25),
        new FlowEdge(2, 5, 48),
        new FlowEdge(1, 8, 52),
        new FlowEdge(1, 9, 56),
        new FlowEdge(1, 2, 65),
        new FlowEdge(1, 3, 79),
        new FlowEdge(1, 4, 3),
        new FlowEdge(5, 7, 12),
        new FlowEdge(5, 1, 17),
        new FlowEdge(5, 3, 70),
        new FlowEdge(5, 6, 56),
        new FlowEdge(5, 9, 2),
        new FlowEdge(7, 9, 73),
        new FlowEdge(7, 4, 50),
        new FlowEdge(7, 6, 25),
        new FlowEdge(4, 1, 54),
        new FlowEdge(4, 2, 25),
        new FlowEdge(4, 3, 16),
        new FlowEdge(4, 5, 39),
        new FlowEdge(6, 1, 63),
        new FlowEdge(6, 9, 2),
        new FlowEdge(6, 7, 27),
        new FlowEdge(3, 8, 8),
        new FlowEdge(3, 2, 71),
        new FlowEdge(3, 6, 43),
        new FlowEdge(3, 7, 10),
        new FlowEdge(0, 8, 53),
        new FlowEdge(0, 2, 29),
        new FlowEdge(0, 3, 75),
        new FlowEdge(0, 6, 73),
        new FlowEdge(0, 7, 42),
        new FlowEdge(8, 4, 31),
        new FlowEdge(8, 5, 32),
        new FlowEdge(8, 6, 11)
    );
    assertThat(Lab5.maxFlow(g, 0, 9)).isEqualTo(133);
  }

  @Test public void generated3() {
    FlowGraph g = new FlowGraph(12,
        new FlowEdge(5, 1, 2),
        new FlowEdge(5, 4, 268),
        new FlowEdge(5, 6, 26),
        new FlowEdge(5, 7, 47),
        new FlowEdge(5, 9, 55),
        new FlowEdge(5, 10, 228),
        new FlowEdge(5, 11, 497),
        new FlowEdge(9, 4, 240),
        new FlowEdge(9, 5, 404),
        new FlowEdge(9, 6, 482),
        new FlowEdge(9, 7, 218),
        new FlowEdge(9, 10, 430),
        new FlowEdge(9, 11, 360),
        new FlowEdge(6, 2, 243),
        new FlowEdge(6, 3, 230),
        new FlowEdge(6, 5, 219),
        new FlowEdge(6, 8, 251),
        new FlowEdge(6, 9, 224),
        new FlowEdge(6, 10, 364),
        new FlowEdge(6, 11, 449),
        new FlowEdge(10, 1, 445),
        new FlowEdge(10, 3, 24),
        new FlowEdge(10, 4, 457),
        new FlowEdge(10, 5, 384),
        new FlowEdge(10, 6, 482),
        new FlowEdge(10, 8, 491),
        new FlowEdge(7, 1, 349),
        new FlowEdge(7, 2, 212),
        new FlowEdge(7, 4, 325),
        new FlowEdge(7, 6, 455),
        new FlowEdge(7, 8, 77),
        new FlowEdge(7, 11, 119),
        new FlowEdge(2, 1, 263),
        new FlowEdge(2, 3, 456),
        new FlowEdge(2, 5, 261),
        new FlowEdge(2, 7, 302),
        new FlowEdge(2, 9, 32),
        new FlowEdge(2, 10, 245),
        new FlowEdge(1, 2, 160),
        new FlowEdge(1, 3, 40),
        new FlowEdge(1, 5, 225),
        new FlowEdge(1, 7, 292),
        new FlowEdge(1, 8, 196),
        new FlowEdge(1, 9, 435),
        new FlowEdge(1, 10, 337),
        new FlowEdge(1, 11, 121),
        new FlowEdge(3, 1, 231),
        new FlowEdge(3, 10, 201),
        new FlowEdge(3, 4, 211),
        new FlowEdge(3, 5, 293),
        new FlowEdge(3, 7, 270),
        new FlowEdge(0, 8, 174),
        new FlowEdge(0, 9, 218),
        new FlowEdge(0, 10, 3),
        new FlowEdge(0, 3, 357),
        new FlowEdge(0, 4, 166),
        new FlowEdge(8, 11, 310),
        new FlowEdge(8, 2, 344),
        new FlowEdge(8, 3, 322),
        new FlowEdge(8, 4, 295),
        new FlowEdge(8, 7, 415),
        new FlowEdge(4, 1, 245),
        new FlowEdge(4, 2, 83),
        new FlowEdge(4, 3, 221),
        new FlowEdge(4, 5, 485),
        new FlowEdge(4, 6, 208),
        new FlowEdge(4, 11, 18)
    );
    assertThat(Lab5.maxFlow(g, 0, 11)).isEqualTo(918);
  }

  @Test public void generated4() {
    FlowGraph g = new FlowGraph(8,
        new FlowEdge(6, 1, 6),
        new FlowEdge(6, 3, 18),
        new FlowEdge(6, 7, 15),
        new FlowEdge(1, 4, 19),
        new FlowEdge(1, 6, 3),
        new FlowEdge(3, 1, 5),
        new FlowEdge(3, 5, 11),
        new FlowEdge(3, 7, 3),
        new FlowEdge(2, 5, 8),
        new FlowEdge(2, 6, 17),
        new FlowEdge(2, 7, 20),
        new FlowEdge(4, 1, 14),
        new FlowEdge(4, 3, 2),
        new FlowEdge(4, 5, 7),
        new FlowEdge(0, 2, 9),
        new FlowEdge(0, 3, 5),
        new FlowEdge(0, 4, 12),
        new FlowEdge(5, 2, 17),
        new FlowEdge(5, 6, 19)
    );
    assertThat(Lab5.maxFlow(g, 0, 7)).isEqualTo(26);
  }

}
