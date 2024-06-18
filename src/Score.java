/**
 * Es wurde versucht, ein score zu zeichnen
 */

public class Score {
    private int points;
  private Score score;

  public Score() {
        this.points = 0;
    }

  public void addPoints(int points) {
    if (this.score != null) {
      this.score.addPoints(points);
    } else {
      // Fehlerbehandlung oder erneute Initialisierung
      this.score = new Score();
      this.score.addPoints(points);
    }
  }


  public int getPoints() {
        return this.points;
    }
}