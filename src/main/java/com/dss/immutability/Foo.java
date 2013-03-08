package com.dss.immutability;

public class Foo {

    private String bar;
    private String baz;

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getBaz() {
        return baz;
    }

    public void setBaz(String baz) {
        this.baz = baz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Foo foo = (Foo) o;

        if (!bar.equals(foo.bar)) return false;
        if (!baz.equals(foo.baz)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bar.hashCode();
        result = 31 * result + baz.hashCode();
        return result;
    }
}
