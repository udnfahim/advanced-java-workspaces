package com.unittesting.defaultClasses;

public class VoterEligibilityUtil {
    // Check if age is valid for voting
    public boolean isValidAge(int age) {
        return age >= 18;
    }

    // Check if citizen is eligible
    public boolean isCitizenEligible(String nationality) {
        return nationality != null && nationality.equalsIgnoreCase("Bangladeshi");
    }

    // Check if NID is valid (10 or 13 digits)
    public boolean isValidNID(String nid) {
        if (nid == null) return false;
        return nid.matches("\\d{10}|\\d{13}");
    }

    // Check if person has criminal record
    public boolean hasCriminalRecord(boolean hasRecord) {
        return hasRecord;
    }

    // Final decision method
    public boolean isEligibleToVote(int age, String nationality, String nid, boolean hasCriminalRecord) {
        return isValidAge(age)
                && isCitizenEligible(nationality)
                && isValidNID(nid)
                && !hasCriminalRecord;
    }
}
