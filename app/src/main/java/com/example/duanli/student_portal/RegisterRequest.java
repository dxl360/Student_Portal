package com.example.duanli.student_portal;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by Peiyan on 2016/10/24.
 */
public class RegisterRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://tonikamitv.hostei.com/Login.php";
    private Map<String, String> params;

    public RegisterRequest(String username, String password, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("userID", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
