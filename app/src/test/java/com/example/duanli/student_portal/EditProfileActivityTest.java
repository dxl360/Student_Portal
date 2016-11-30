package com.example.duanli.student_portal;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by alw on 2016/11/20.
 */

public class EditProfileActivityTest {
    @Test
    public void checkAgeTest(){
        EditProfileActivity ea = new EditProfileActivity();
        ea.ageChecker("102", true);
        assertThat(ea.success, is(false));
        ea.ageChecker("-9", true);
        assertThat(ea.success, is(false));
        ea.ageChecker("", true);
        assertThat(ea.success, is(false));
        ea.ageChecker("10", true);
        assertThat(ea.success, is(true));

    }
    @Test
    public void checkEmailTest() {

        EditProfileActivity ea = new EditProfileActivity();
        ea.emailChecker("xx@case.edu", true);
        assertThat(ea.success, is(true));
        ea.emailChecker("cc", true);
        assertThat(ea.success, is(false));
        ea.emailChecker("", true);
        assertThat(ea.success, is(false));
    }

    @Test
    public void checkPhoneNumberTest() {

        EditProfileActivity ea = new EditProfileActivity();
        ea.phoneNumberChecker("123459393939a393", true);
        assertThat(ea.success, is(false));
        ea.phoneNumberChecker("11111", true);
        assertThat(ea.success, is(false));
        ea.phoneNumberChecker("8288289192", true);
        assertThat(ea.success, is(true));
        ea.phoneNumberChecker("", true);
        assertThat(ea.success, is(false));
    }

}
