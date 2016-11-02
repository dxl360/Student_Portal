package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class TimeValidatorTest{

    @Test
    public void timeValidator_CorrectTimeSimple_ReturnsTrue() {
        assertEquals(NewEventActivity.isValidTime("10:00"), true);
    }

}