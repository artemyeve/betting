package by.artemyeu.betting.entity;


import java.util.Date;

/**
 * Created by Acer on 16.05.2017.
 */
public class Match extends Entity {
    private int matchId;
    private String tournament;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private Date matchDate;
    private boolean active;



    /**
     * Instantiates a new match.
     *
     * @param matchId the match id
     * @param tournament the tournament
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @param homeTeamGoals the home team goals
     * @param awayTeamGoals the away team goals
     * @param matchDate the match date
     * @param active the status of the match
     */
    public Match(int matchId, String tournament, String homeTeam, String awayTeam, int homeTeamGoals,
                 int awayTeamGoals, Date matchDate, boolean active) {
        this.matchId = matchId;
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.matchDate = matchDate;
        this.active = active;

    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
