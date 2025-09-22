/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.ArrayList;
import java.util.List;

public class ClosestWords {
  ArrayList<String> closestWords = new ArrayList<>();
  int[][] M;
  int closestDistance = Integer.MAX_VALUE -50;

  int partDist(String w1, String w2, int w1len, int w2len, int index) {
    if (w1len == 0){
      return w2len;
    }
    if (w2len == 0){
      return w1len;
    }
    //int min;
    for (int i = 1; i <= w1len; i++){
      //min = Integer.MAX_VALUE - 1;
      for (int j = index + 1; j <= w2len; j++){
        int res = M[i-1][j-1] + (w1.charAt(i-1) == w2.charAt(j-1) ? 0 : 1);
        int deleteLetter = M[i-1][j] + 1;
        if (deleteLetter < res){
          res = deleteLetter;
        }
        int addletter = M[i][j-1] + 1;
        if (addletter < res){
          res = addletter;
        }
        M[i][j] = res;
        //if (M[i][j] < min){
        //  min = M[i][j];
        //}
      }
      //if (min > closestDistance){
      //  return w1len;
      //}
    }
    return M[w1len][w2len];
  }

  int distance(String w1, String w2, int numLettersAlike) {
    return partDist(w1, w2, w1.length(), w2.length(), numLettersAlike);
  }

  public ClosestWords(String w, List<String> wordList) {
    int longestWord = 40;
    M = new int[w.length()+1][longestWord+1];
    for (int i = 0; i <= w.length(); i++) {
      M[i][0] = i;
    }
    for (int i = 0; i <= longestWord; i++) {
      M[0][i] = i;
    }
    String previouString = "";
    for (String s : wordList) {
      //if(((s.length() > closestDistance + w.length()) || (s.length() < w.length() - closestDistance)) && closestDistance > -1){
      //  continue;
      //}
      int i = 0;
      int min = previouString.length();
      if (s.length() < min){
        min = s.length();
      }
      while (i < min){
        if (previouString.charAt(i) != s.charAt(i)){
          break;
        }
        i++;
      }
      int dist = distance(w, s, i);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance) {
        closestDistance = dist;
        closestWords.clear();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
      previouString = s;
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
