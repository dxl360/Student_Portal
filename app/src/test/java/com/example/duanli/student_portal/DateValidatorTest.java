package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class DateValidatorTest{

    @Test
    public void dateValidator_CorrectDateSimple_ReturnsTrue() {
        assertEquals(NewEventActivity.isValidDate("2016-08-05"), true);
    }

}