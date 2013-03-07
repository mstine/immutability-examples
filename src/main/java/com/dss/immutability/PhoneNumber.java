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
}
