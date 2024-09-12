// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab3;

/**
 * Returns true only if both words have the same length and differ in one character.
 * (Does not need to handle variable length characters.)
 */
public class OneLetterDiff implements WordCriteria {
  @Override public boolean adjacent(String word1, String word2) {
    // TODO(D2): implement this!
    if (word1.length() != word2.length()) {
      return false;
    }

    if (word1.length() == 0 && word2.length() == 0) {
      return false;
    }

    int mistakes = 1;
    if (word1.length() == word2.length()) {
      for (int i = 0; i < word1.length(); i++) {
        if (word1.charAt(i) != word2.charAt(i)) {
          mistakes--;
          if (mistakes < 0) {
            return false;
          }

        }
      }
    }

    if (mistakes == 1) {
      return false;
    }
    return true;
  }

}

