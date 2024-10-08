package com.houstondirectauto.refurb.enums;

public class ServiceEnums {
    public enum Status {
        PENDING, ACTIVE
    }

    public enum IsDefaultService {
        YES("1"), NO("0");

        private final String value;

        IsDefaultService(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum TimeType {
        MIN, HOUR
    }
}
