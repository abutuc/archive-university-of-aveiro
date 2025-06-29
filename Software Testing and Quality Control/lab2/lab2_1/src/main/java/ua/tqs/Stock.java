package ua.tqs;

public class Stock {
    private String label;
    private Integer quantity;

    public Stock(String label, Integer quantity) {
        this.label = label;
        this.quantity = quantity;
    }

    public String getLabel() {
        return label;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" + "label=" + label + ", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock other = (Stock) obj;
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        if (this.quantity != other.quantity && (this.quantity == null || !this.quantity.equals(other.quantity))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 97 * hash + (this.quantity != null ? this.quantity.hashCode() : 0);
        return hash;
    }
}
