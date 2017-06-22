package by.artemyeu.betting.entity;


import java.math.BigDecimal;

/**
 * Created by Acer on 01.06.2017.
 */
public class Account extends Entity {
    private int id;
    private BigDecimal balance;



    /**
     * Instantiates a new cash account.
     *
     * @param id the id
     * @param balance the balance
     */
    public Account(int id,  BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
