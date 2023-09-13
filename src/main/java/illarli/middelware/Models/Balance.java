package illarli.middelware.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Balance {
    @Id
    private String id;
    private String port;
    private int getWeightTimer;
    @OneToOne
    @JoinColumn(name = "balanceType", referencedColumnName = "id")
    private BalanceType balanceType;

    public Balance(String id, String port, int getWeightTimer, BalanceType balanceType) {
        this.id = id;
        this.port = port;
        this.getWeightTimer = getWeightTimer;
        this.balanceType = balanceType;
    }

    protected Balance() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getGetWeightTimer() {
        return getWeightTimer;
    }

    public void setGetWeightTimer(int getWeightTimer) {
        this.getWeightTimer = getWeightTimer;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }
}
