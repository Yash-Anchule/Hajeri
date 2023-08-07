package com.example.attendancechecker.main.profile;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancechecker.R;
import com.example.attendancechecker.main.AppBase;


public class EditStudentActivity extends AppCompatActivity {
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__student);

        Button loadButton = (Button) findViewById(R.id.loadForEdit);
        assert loadButton != null;
        loadButton.setOnClickListener(v -> {
            EditText adm = (EditText) findViewById(R.id.register_);
            String qu = "SELECT * FROM STUDENT WHERE regno = '" + adm.getText().toString().toUpperCase() + "'";
            Cursor cr = AppBase.handler.execQuery(qu);
            if (cr == null || cr.getCount() == 0) {
                Toast.makeText(getBaseContext(), "No Such Student", Toast.LENGTH_LONG).show();
            } else {
                cr.moveToFirst();
                try {
                    EditText name = (EditText) findViewById(R.id.edit_name_);
                    EditText roll = (EditText) findViewById(R.id.roll_);
                    EditText contact = (EditText) findViewById(R.id.contact_);
                    assert name != null;
                    name.setText(cr.getString(0));
                    assert roll != null;
                    roll.setText(cr.getString(4));
                    assert contact != null;
                    contact.setText(cr.getString(3));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Button saveEdit = (Button) findViewById(R.id.buttonSAVEEDITS);
        assert saveEdit != null;
        saveEdit.setOnClickListener(v -> {
            EditText name = (EditText) findViewById(R.id.edit_name_);
            EditText roll = (EditText) findViewById(R.id.roll_);
            EditText contact = (EditText) findViewById(R.id.contact_);
            EditText adm = (EditText) findViewById(R.id.register_);

            String qu = "UPDATE STUDENT SET name = '" + name.getText().toString() + "' , " +
                    " roll = " + roll.getText().toString() + " , contact = '" + contact.getText().toString() + "' " +
                    "WHERE regno = '" + adm.getText().toString().toUpperCase() + "'";
            Log.d("EditStudentActivity", qu);
            if (AppBase.handler.execAction(qu)) {
                Toast.makeText(getBaseContext(), "Edit Saved", Toast.LENGTH_LONG).show();
                activity.finish();

            } else
                Toast.makeText(getBaseContext(), "Error Occured", Toast.LENGTH_LONG).show();

        });
    }

}
