package kz.itstep.entity;

public class PurchasedCource extends Entity {
    private int userId;
    private int courceId;

    public PurchasedCource(int userId, int courceId) {
        this.userId = userId;
        this.courceId = courceId;
    }

    public PurchasedCource(){}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourceId() {
        return courceId;
    }

    public void setCourceId(int courceId) {
        this.courceId = courceId;
    }
}
