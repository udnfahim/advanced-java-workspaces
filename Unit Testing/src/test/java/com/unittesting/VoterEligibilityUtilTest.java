package com.unittesting;

import com.unittesting.defaultClasses.VoterEligibilityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VoterEligibilityUtilTest {

    private VoterEligibilityUtil eligibilityUtil;

    @BeforeEach
    void setup(){
        eligibilityUtil = new VoterEligibilityUtil();
    }

    @Test
    void testAgeValidation(){
        Assertions.assertTrue(eligibilityUtil.isValidAge(18));
        Assertions.assertFalse(eligibilityUtil.isValidAge(17));
    }

    @Test
    void testCitizenshipValidation(){
        Assertions.assertTrue(eligibilityUtil.isCitizenEligible("Bangladeshi"));
        Assertions.assertTrue(eligibilityUtil.isCitizenEligible("bangladeshi"));
        Assertions.assertFalse(eligibilityUtil.isCitizenEligible("Indian"));
        Assertions.assertFalse(eligibilityUtil.isCitizenEligible(null));
    }

    @Test
    void testNIDValidation(){
        Assertions.assertTrue(eligibilityUtil.isValidNID("1234567890"));
        Assertions.assertTrue(eligibilityUtil.isValidNID("1234567890123"));
        Assertions.assertFalse(eligibilityUtil.isValidNID("12345"));
        Assertions.assertFalse(eligibilityUtil.isValidNID(null));
    }

    @Test
    void testCriminalRecord(){
        Assertions.assertFalse(eligibilityUtil.isEligibleToVote(25, "Bangladeshi", "1234567890", true));
        Assertions.assertTrue(eligibilityUtil.isEligibleToVote(25, "Bangladeshi", "1234567890", false));
    }

    @Test
    void testFinalEligibility() {

        Assertions.assertTrue(eligibilityUtil.isEligibleToVote(25, "Bangladeshi", "1234567890", false));
        Assertions.assertFalse(eligibilityUtil.isEligibleToVote(16, "Bangladeshi", "1234567890", false));
        Assertions.assertFalse(eligibilityUtil.isEligibleToVote(30, "Indian", "1234567890", false));
        Assertions.assertFalse(eligibilityUtil.isEligibleToVote(40, "Bangladeshi", "12345", false));
        Assertions.assertFalse(eligibilityUtil.isEligibleToVote(28, "Bangladeshi", "1234567890", true));
    }
}
/*
VoterEligibilityUtil class:
*. Test Age Validation     - Age = 18 → valid
     - Age = 17 → invalid

*. Test Citizenship Validation
   - "Bangladeshi" → valid
   - "bangladeshi" → valid
   - "Indian" → invalid
   - null → invalid

*. Test NID Validation
    - "1234567890" → valid
    - "1234567890123" → valid
    - "12345" → invalid
    - null → invalid

*.  Test Criminal Record
   - true → not eligible
   - false → eligible


*. Test Final EligibilityAge    Nationality       NID                 Criminal Record    Eligibility
25       Bangladeshi    1234567890      false                      true
16       Bangladeshi    1234567890      false                      false
30      Indian               1234567890      false                      false
40      Bangladeshi    12345                 false                      false
28       Bangladeshi    1234567890     true                        false
 */