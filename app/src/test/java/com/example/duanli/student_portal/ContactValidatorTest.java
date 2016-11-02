package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class ContactValidatorTest{

    @Test
    public void contactValidator_CorrectContactSimple_ReturnsTrue() {
        assertEquals(NewEventActivity.isValidContact("216 555 3333"), true);
    }

}