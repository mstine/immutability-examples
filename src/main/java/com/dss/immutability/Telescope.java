package com.dss.immutability;

public final class Telescope {

    // Required fields
    private final String a;
    private final String b;
    private final String c;
    // Optional fields
    private final String d;
    private final String e;
    private final String f;

    public Telescope(String a, String b, String c, String d, String e, String f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    public Telescope(String a, String b, String c) {
        this(a, b, c, null, null, null);
    }

    public Telescope(String a, String b, String c, String d) {
        this(a, b, c, d, null, null);
    }

    public Telescope(String a, String b, String c, String d, String e) {
        this(a, b, c, d, e, null);
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getE() {
        return e;
    }

    public String getF() {
        return f;
    }
}