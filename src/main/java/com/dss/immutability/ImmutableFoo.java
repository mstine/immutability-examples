package com.dss.immutability;

public class ImmutableFoo {

    private final String bar;
    private final String baz;

    private ImmutableFoo(Builder builder) {
        this.bar = builder.bar;
        this.baz = builder.baz;
    }

    public String getBar() {
        return bar;
    }

    public String getBaz() {
        return baz;
    }

    public static class Builder {
        private String bar;
        private String baz;

        public Builder bar(String bar) {
            this.bar = bar;
            return this;
        }

        public Builder baz(String baz) {
            this.baz = baz;
            return this;
        }

        public ImmutableFoo build() {
            return new ImmutableFoo(this);
        }
    }
}
