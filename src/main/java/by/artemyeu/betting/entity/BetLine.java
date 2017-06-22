package by.artemyeu.betting.entity;


/**
 * Created by Acer on 01.06.2017.
 */
public class BetLine extends Entity {
    private int betLineId;
    private int matchId;
    private Outcome outcome;
    private double odd;


    /**
     * Instantiates a new bet line.
     *
     * @param betLineId the bet line id
     * @param matchId the match id
     * @param outcome the outcome type
     * @param odd the odd
     */
     public BetLine(int betLineId, int matchId, Outcome outcome, double odd) {
        this.betLineId = betLineId;
        this.matchId = matchId;
        this.outcome = outcome;
        this.odd = odd;

    }

    public int getBetLineId() {
        return betLineId;
    }

    public void setBetLineId(int betLineId) {
        this.betLineId = betLineId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public double getOdd() {
        return odd;
    }

    public void setOdd(double odd) {
        this.odd = odd;
    }

}

