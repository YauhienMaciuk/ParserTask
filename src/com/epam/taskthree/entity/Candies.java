package com.epam.taskthree.entity;

public class Candies extends Confection {
    private String type;

    public Candies() {
    }

    public Candies(String name, double weight, double quantitySugar, String type) {
        super(name, weight, quantitySugar);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Candies candies = (Candies) o;

        if (!type.equals(candies.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append('\n').append("Candies: ").append("type = ").append(type)
                .append(super.toString()).toString();
    }
}
