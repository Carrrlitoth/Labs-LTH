// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab3;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assert_;

public class TestWordLoading {

  /** Test that WordGraph loads the words from the word file. */
  @Test public void testWords1() throws IOException {
    WordGraph graph = new WordGraph(Paths.get("words-1.txt"), new OneLetterDiff());
    assertThat(graph.vertexSet())
        .containsExactly("kall", "vall", "vals", "vars", "varm", "barm", "karm", "tarm");
  }

  /** Test that WordGraph loads the words from the word file. */
  @Test public void testWords2() throws IOException {
    WordGraph graph = new WordGraph(Paths.get("words-2.txt"), new OneLetterDiff());
    assertThat(graph.vertexSet())
        .containsExactly("green", "greet", "great", "groat", "groan", "grown", "brown");
  }

}
