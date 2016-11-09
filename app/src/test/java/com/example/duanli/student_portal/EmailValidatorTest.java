package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class EmailValidatorTest{

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(NewEventActivity.isValidEmail("name@email.com"), true);
    }

}