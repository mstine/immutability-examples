package com.dss.immutability;

public class PhoneNumber {

    private final String areaCode;
    private final String prefix;
    private final String lineNumber;

    public PhoneNumber(String areaCode, String prefix, String lineNumber) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (!areaCode.equals(that.areaCode)) return false;
        if (!lineNumber.equals(that.lineNumber)) return false;
        if (!prefix.equals(that.prefix)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = areaCode.hashCode();
        result = 31 * result + prefix.hashCode();
        result = 31 * result + lineNumber.hashCode();
        return result;
    }
}
