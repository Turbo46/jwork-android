package gilbert.jwork_android;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.app.AlertDialog;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    private ArrayList<Recruiter> listRecruiter = new ArrayList<>();
    private ArrayList<Job> jobIdList = new ArrayList<>();
    private HashMap<Recruiter, ArrayList<Job>> childMapping = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        refreshList();
    }

    protected void refreshList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i=0; i<jsonResponse.length(); i++) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                        JSONObject job = jsonResponse.getJSONObject(i);
                        JSONObject recruiter = job.getJSONObject("recruiter");
                        JSONObject location = recruiter.getJSONObject("location");

                        Location newLocation = new Location(
                                location.getString("province"),
                                location.getString("description"),
                                location.getString("city")
                        );

                        Recruiter newRecruiter = new Recruiter(
                                recruiter.getInt("id"),
                                recruiter.getString("name"),
                                recruiter.getString("email"),
                                recruiter.getString("phoneNumber"),
                                newLocation
                        );

                            Job newJob = new Job(
                                job.getInt("id"),
                                job.getString("name"),
                                job.getInt("price"),
                                job.getString("category"),
                                newRecruiter
                        );

                        jobIdList.add(newJob);

                        boolean tempStatus = true;
                        for(Recruiter recruiterPtr : listRecruiter) {
                            if(recruiterPtr.getId() == newRecruiter.getId()){
                                tempStatus = false;
                            }
                        }
                        if(tempStatus==true){
                            listRecruiter.add(newRecruiter);
                        }
                    }

                    for(Recruiter recruiterPtr : listRecruiter){
                        ArrayList<Job> tempJobList = new ArrayList<>();
                        for(Job jobPtr : jobIdList){
                            if(jobPtr.getRecruiter().getId() == recruiterPtr.getId()){
                                tempJobList.add(jobPtr);
                            }
                        }
                        childMapping.put(recruiterPtr, tempJobList);
                    }
                    listAdapter = new MainListAdapter(MainActivity.this, listRecruiter, childMapping);
                    expListView.setAdapter(listAdapter);
                }
                catch (JSONException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Load Data Failed.").create().show();
                }
            }
        };

        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }
}