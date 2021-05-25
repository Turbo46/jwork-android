package gilbert.jwork_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static String URL = "http://10.12.7.154/customer/register";
    private Map<String, String> params;

    public RegisterRequest(String name, String email, String password, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return params;
    }
}