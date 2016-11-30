package com.example.duanli.student_portal;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Peiyan on 2016/10/24.
 */
public class EditProfileRequest extends StringRequest {
    private static final String EDIT_PROFILE_REQUEST_URL = "http://tonikamitv.hostei.com/Login.php";
    private Map<String, String> params;

    public EditProfileRequest(String username, String userID, Response.Listener<String> listener) {
        super(Method.POST, EDIT_PROFILE_REQUEST_URL, listener, null);
        params = new HashMap<>();

        params.put("Username", username);
        //Database input phone number, age, email.
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
