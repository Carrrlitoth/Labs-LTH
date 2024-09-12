// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab3;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;

public class TestWordEdges {

  /** Test that WordGraph loads the words from the word file. */
  @Test public void test7words() throws IOException {
    WordGraph graph = new WordGraph(Paths.get("words-2.txt"), new OneLetterDiff());
    assertThat(graph.neighbours("green")).containsExactly("greet");
    assertThat(graph.neighbours("greet")).containsExactly("green", "great");
    assertThat(graph.neighbours("great")).containsExactly("greet", "groat");
    assertThat(graph.neighbours("groat")).containsExactly("great", "groan");
    assertThat(graph.neighbours("groan")).containsExactly("groat", "grown");
    assertThat(graph.neighbours("grown")).containsExactly("groan", "brown");
    assertThat(graph.neighbours("brown")).containsExactly("grown");
  }

}
