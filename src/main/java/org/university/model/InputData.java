package org.university.model;

public class InputData {
    private int m;
    private int a;
    private int c;
    private int x0;
    private int outputNumber;

    public InputData(int m, int a, int c, int x0, int outputNumber) {
        this.m = m;
        this.a = a;
        this.c = c;
        this.x0 = x0;
        this.outputNumber = outputNumber;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getOutputNumber() {
        return outputNumber;
    }

    public void setOutputNumber(int outputNumber) {
        this.outputNumber = outputNumber;
    }
}
