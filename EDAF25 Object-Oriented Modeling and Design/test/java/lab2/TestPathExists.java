// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab2;

import graph.Graph;
import graph.SimpleGraph;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestPathExists {

  @Test
  public final void testOneVertex() {
    Graph<Integer> g = new SimpleGraph(1);
    assertThat(Lab2.pathExists(g, 0, 0)).isTrue();
  }

  @Test
  public final void testTwoVerticesNoPath() {
    Graph<Integer> g = new SimpleGraph(2);
    assertThat(Lab2.pathExists(g, 0, 1)).isFalse();
  }

  @Test
  public final void testTwoVerticesPathExists1() {
    Graph<Integer> g = new SimpleGraph(2, new int[][]{
        {0, 1}, {1, 0},
    });
    assertThat(Lab2.pathExists(g, 0, 1)).isTrue();
    assertThat(Lab2.pathExists(g, 1, 0)).isTrue();
  }

  @Test
  public final void testTwoVerticesPathExists2() {
    Graph<Integer> g = new SimpleGraph(2, new int[][]{{1, 0}});
    assertThat(Lab2.pathExists(g, 0, 1)).isFalse();
    assertThat(Lab2.pathExists(g, 1, 0)).isTrue();
  }

  @Test
  public final void testThreeVerticesPathsExist() {
    Graph<Integer> g = new SimpleGraph(3, new int[][]{
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
    });
    assertThat(Lab2.pathExists(g, 0, 1)).isTrue();
    assertThat(Lab2.pathExists(g, 1, 2)).isTrue();
    assertThat(Lab2.pathExists(g, 2, 0)).isTrue();
  }

  @Test
  public final void testThreeVerticesNoPath() {
    Graph<Integer> g = new SimpleGraph(3, new int[][]{
        {1, 2}, {2, 1}
    });
    assertThat(Lab2.pathExists(g, 0, 1)).isFalse();
    assertThat(Lab2.pathExists(g, 0, 2)).isFalse();
    assertThat(Lab2.pathExists(g, 1, 2)).isTrue();
  }

  @Test
  public final void testFiveVerticesPathsExist() {
    Graph<Integer> g = new SimpleGraph(5, new int[][]{
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
        {1, 2}, {2, 1},
        {0, 3}, {3, 0},
        {3, 4}, {4, 3},
    });
    assertThat(Lab2.pathExists(g, 0, 1)).isTrue();
    assertThat(Lab2.pathExists(g, 1, 2)).isTrue();
    assertThat(Lab2.pathExists(g, 2, 3)).isTrue();
    assertThat(Lab2.pathExists(g, 3, 4)).isTrue();
    assertThat(Lab2.pathExists(g, 4, 0)).isTrue();
  }

  @Test
  public final void testFiveVerticesTwoComponents() {
    Graph<Integer> g = new SimpleGraph(5, new int[][]{
        {0, 1}, {1, 0},
        {0, 2}, {2, 0},
        {1, 2}, {2, 1},
        {3, 4}, {4, 3},
    });
    assertThat(Lab2.pathExists(g, 0, 3)).isFalse();
    assertThat(Lab2.pathExists(g, 1, 3)).isFalse();
    assertThat(Lab2.pathExists(g, 2, 3)).isFalse();
    assertThat(Lab2.pathExists(g, 4, 0)).isFalse();
    assertThat(Lab2.pathExists(g, 4, 1)).isFalse();
    assertThat(Lab2.pathExists(g, 4, 2)).isFalse();
    assertThat(Lab2.pathExists(g, 0, 1)).isTrue();
    assertThat(Lab2.pathExists(g, 2, 0)).isTrue();
  }

  @Test
  public final void testFiveVerticesFiveComponents() {
    Graph<Integer> g = new SimpleGraph(5);
    assertThat(Lab2.pathExists(g, 0, 1)).isFalse();
    assertThat(Lab2.pathExists(g, 1, 2)).isFalse();
    assertThat(Lab2.pathExists(g, 2, 3)).isFalse();
    assertThat(Lab2.pathExists(g, 3, 4)).isFalse();
    assertThat(Lab2.pathExists(g, 4, 0)).isFalse();
  }

  @Test
  public final void test6() {
    Graph<Integer> g = new SimpleGraph(8, new int[][]{
        {0, 1}, {1, 0},
        {1, 2}, {2, 1},
        {2, 3}, {3, 2},
        {2, 4}, {4, 2},
        {2, 5}, {5, 2},
        {4, 6}, {6, 4},
        {5, 7}, {7, 5},
    });
    assertThat(Lab2.pathExists(g, 0, 6)).isTrue();
    assertThat(Lab2.pathExists(g, 0, 7)).isTrue();
  }

}
