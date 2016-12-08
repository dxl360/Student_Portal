package com.example.duanli.student_portal;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by alw on 2016/11/20.
 */

public class EditProfileActivityTest {
    @Test
    public void ValidRegistrationTest() {
        EditProfileActivity ra = new EditProfileActivity();

        //Test false

        //test invalid password
        ra.validation("fff@case.edu", "bb", true);
        assertThat(ra.success, is(false));
        //test invalid username
        ra.validation("fff", "bb", true);
        assertThat(ra.success, is(false));

        //Test empty
        ra.validation("", "bb", true);
        assertThat(ra.success, is(false));
        ra.validation("fff@case.edu", "", true);
        assertThat(ra.success, is(false));

        //Test true

        //test valid password and username
        //length 8-20, at least 1 uppercase, 1 lowercase, 1 number
        ra.validation("fff@case.edu", "Abc123456", true);
        assertThat(ra.success, is(true));
    }

}
