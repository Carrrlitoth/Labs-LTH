// NOT TO BE PUBLISHED ON GITHUB. DO NOT SHARE LAB SOLUTIONS.
package lab3;

/** Criteria for two words to be adjacent in a word ladder game. */
public interface WordCriteria {
  boolean adjacent(String word1, String word2);
}
