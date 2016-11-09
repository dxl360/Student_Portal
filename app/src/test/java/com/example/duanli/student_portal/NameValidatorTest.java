package com.example.duanli.student_portal;

import org.junit.Test;
import java.util.regex.Pattern;
import static org.junit.Assert.*;

public class NameValidatorTest{

    @Test
    public void nameValidator_CorrectEmailSimple_ReturnsTrue() {
        assertEquals(NewEventActivity.isValidName("Mark"), true);
    }

}