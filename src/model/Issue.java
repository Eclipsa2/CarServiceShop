package model;

public class Issue {
    private String issueName;
    private int price;

    public Issue(String issueName, int price) {
        this.issueName = issueName;
        this.price = price;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
