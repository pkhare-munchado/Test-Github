package com.android.gifts.butterknife;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.gifts.butterknife.EventModel.ClickEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_fname)
    protected EditText txtFirstName;
    @BindView(R.id.edit_text_lname)
    protected EditText txtLastName;
    @BindView(R.id.fragment_container)
    protected FrameLayout fragmentContainer;
    @BindString(R.string.alert_both_field_mandatory)
    String invalidMsg;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new FragmentA())
                .commit();
    }

    @OnClick(R.id.make_toast_button)
    public void sayHi(Button button) {
        if (!TextUtils.isEmpty(txtFirstName.getText().toString()) && !TextUtils.isEmpty(txtLastName.getText().toString())) {
            // we defined a specific type as input (Button button), and it will automatically be cast.
            // so we can control it easily like this
            button.setText("Toast Done");
            // if don't need to create model class then you can use simple String as object and subscriber can subscribe OnEvent method with String parameter.
//            bus.post(new ClickEvent("Clicked"));
            bus.post("Clicked");
        } else
            Toast.makeText(getApplicationContext(), invalidMsg, Toast.LENGTH_SHORT).show();


    }

}