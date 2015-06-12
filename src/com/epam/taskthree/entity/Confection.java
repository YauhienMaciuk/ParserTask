package com.epam.taskthree.entity;

import com.epam.taskthree.exception.LogicException;

public abstract class Confection {
    private String name;
    private double weight;
    private double quantitySugar;

    public Confection() {
    }

    public Confection(String name, double weight, double quantitySugar) {
        this.name = name;
        this.weight = weight;
        this.quantitySugar = quantitySugar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws LogicException {
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new LogicException("Некорректно задан вес сладости");
        }
    }

    public double getQuantitySugar() {
        return quantitySugar;
    }

    public void setQuantitySugar(double quantitySugar) throws LogicException {
        if (quantitySugar >= 0) {
            this.quantitySugar = quantitySugar;
        } else {
            throw new LogicException("Некорректно задано количество сахара");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confection that = (Confection) o;

        if (Double.compare(that.quantitySugar, quantitySugar) != 0) return false;
        if (Double.compare(that.weight, weight) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(quantitySugar);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(", name = '").append(name).append("\'").append(", weight = ").append(weight)
                .append(", quantitySugar = ").append(quantitySugar).toString();

    }
}
