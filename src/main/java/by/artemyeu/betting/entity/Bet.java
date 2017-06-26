package by.artemyeu.betting.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Created by Acer on 16.05.2017.
 */
public class Bet extends Entity {
    private int betId;
    private BigDecimal betAmount;
    private Date betDate;
    private boolean active;
    private int userId;


    /**
     * Instantiates a new bet.
     *
     * @param betId the bet id
     * @param betAmount the bet amount
     * @param betDate the bet date
     * @param active the bet active/inactive
     * @param userId the user id
     */
    public Bet(int betId, BigDecimal betAmount, Date betDate, boolean active, int userId) {
        this.betId = betId;
        this.betAmount = betAmount.setScale(4, RoundingMode.DOWN);;
        this.betDate = betDate;
        this.active = active;
        this.userId = userId;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public Date getBetDate() {
        return betDate;
    }

    public void setBetDate(Date betDate) {
        this.betDate = betDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
