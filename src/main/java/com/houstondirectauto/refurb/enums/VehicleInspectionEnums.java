package com.houstondirectauto.refurb.enums;

public class VehicleInspectionEnums {

    public enum Status {
        PENDING, ON_HOLD, ASSIGNED, WORKING, COMPLETE, APPROVED, DELETED, DELETED_BY_MANAGER, DENIED
    }

    public enum ApprovalStatus {
        PENDING, ACTIVE, APPROVE_AND_MOVE, DENIED, APPROVE_AS_IS
    }

    public enum AddedIn {
        APPROVAL, INSPECTION, IN_REVIEW
    }

    public enum TimeType {
        MIN, HOUR
    }
}
