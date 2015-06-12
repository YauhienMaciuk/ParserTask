package com.epam.taskthree.entity;

public class Chocolate extends Confection {
    private String color;

    public Chocolate() {
    }

    public Chocolate(String name, double weight, double quantitySugar, String color) {
        super(name, weight, quantitySugar);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Chocolate chocolate = (Chocolate) o;

        if (!color.equals(chocolate.color)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append('\n').append("Chocolate: ").append("color = ").append(color)
                .append(super.toString()).toString();
    }
}
