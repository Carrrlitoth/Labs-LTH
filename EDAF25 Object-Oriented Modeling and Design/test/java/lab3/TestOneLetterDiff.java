// PROPRIETARY COURSE MATERIAL. DO NOT PUBLISH.
// DO NOT UPLOAD TO A PUBLIC REPOSITORY.
// Author: Jesper Öqvist (Lund University)
package lab3;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TestOneLetterDiff {
  private static final OneLetterDiff diff = new OneLetterDiff();

  /** The empty word should not normally occur in the word graph. */
  @Test public void testEmpty() {
    assertThat(diff.adjacent("", "")).isFalse();
  }

  /** Equal words are not adjacent. */
  @Test public void test0() {
    assertThat(diff.adjacent("a", "a")).isFalse();
    assertThat(diff.adjacent("hi", "hi")).isFalse();
  }

  /** Different cases are counted as different letters. */
  @Test public void testCaseDiff() {
    assertThat(diff.adjacent("a", "A")).isTrue();
    assertThat(diff.adjacent("A", "a")).isTrue();
    assertThat(diff.adjacent("Xy", "xy")).isTrue();
    assertThat(diff.adjacent("bab", "bAb")).isTrue();
    assertThat(diff.adjacent("xy", "XY")).isFalse();
  }

  @Test public void test1() {
    assertThat(diff.adjacent("foo", "bar")).isFalse();
    assertThat(diff.adjacent("bar", "foo")).isFalse();
  }

  @Test public void test2() {
    assertThat(diff.adjacent("foo", "fob")).isTrue();
    assertThat(diff.adjacent("fob", "foo")).isTrue();
  }

  @Test public void test3() {
    assertThat(diff.adjacent("foo", "fOO")).isFalse();
    assertThat(diff.adjacent("foo", "fOo")).isTrue();
  }

  @Test public void test4() {
    assertThat(diff.adjacent("foo", "fooo")).isFalse();
    assertThat(diff.adjacent("foo", "fo")).isFalse();
    assertThat(diff.adjacent("fooo", "foo")).isFalse();
    assertThat(diff.adjacent("fo", "foo")).isFalse();
  }
}
