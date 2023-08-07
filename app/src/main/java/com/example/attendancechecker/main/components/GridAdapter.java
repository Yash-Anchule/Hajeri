package com.example.attendancechecker.main.components;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.attendancechecker.R;
import com.example.attendancechecker.main.AppBase;
import com.example.attendancechecker.main.attendance.AttendanceActivity;
import com.example.attendancechecker.main.notes.NoteActivity;
import com.example.attendancechecker.main.profile.ProfileActivity;
import com.example.attendancechecker.main.schedule.Scheduler;

import java.util.ArrayList;


public class GridAdapter extends BaseAdapter {
    public static Activity activity;
    ArrayList<String> names;

    public GridAdapter(Activity activity, ArrayList<String> names) {
        GridAdapter.activity = activity;
        this.names = names;
    }

    public static void makeNotification(String userIntrouble) {
        Log.d("NOTIFICATION", "Building..........");
        Intent notificationIntent = new Intent(activity.getApplicationContext(), NoteActivity.class);
//        notificationIntent.putExtra(MainListAdapter.USER_EMAIL,userIntrouble);
//        notificationIntent.putExtra(MainListAdapter.IS_EMERGENCY, true);
        PendingIntent pIntent = PendingIntent.getActivity(activity, 0, notificationIntent,
                0);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(activity.getBaseContext());
        Uri ring = Uri.parse(sharedPrefs.getString("Notification_Sound", Settings.System.DEFAULT_RINGTONE_URI.toString()));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(activity.getBaseContext())
                .setTicker("Ticker Title").setContentTitle("Notes Are Available For this subject")
                .setSmallIcon(R.drawable.ic_notes)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(userIntrouble))
                .setContentIntent(pIntent)
                .setSound(ring);

        Notification noti = builder.build();
        noti.contentIntent = pIntent;
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(activity);
            v = vi.inflate(R.layout.grid_layout, null);
        }
        TextView textView = (TextView) v.findViewById(R.id.namePlacer);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageHolder);
        switch (names.get(position)) {
            case "ATTENDANCE": {
                imageView.setImageResource(R.drawable.ic_attendance);
                v.setOnClickListener(v1 -> {
                    FragmentManager fm = activity.getFragmentManager();
                    createRequest request = new createRequest();
                    request.show(fm, "Select");
                });
                Animation anim = new ScaleAnimation(
                        0.95f, 1f, // Start and end values for the X axis scaling
                        0.95f, 1f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

                anim.setFillAfter(true); // Needed to keep the result of the animation

                anim.setDuration(2000);
                anim.setRepeatMode(Animation.INFINITE);
                anim.setRepeatCount(Animation.INFINITE);
                imageView.startAnimation(anim);

                break;
            }
            case "SCHEDULER": {
                imageView.setImageResource(R.drawable.ic_schedule);
                v.setOnClickListener(v12 -> {
                    Intent launchinIntent = new Intent(activity, Scheduler.class);
                    activity.startActivity(launchinIntent);
                });
                Animation anim = new ScaleAnimation(
                        0.95f, 1f, // Start and end values for the X axis scaling
                        0.95f, 1f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

                anim.setFillAfter(true); // Needed to keep the result of the animation

                anim.setDuration(2000);
                anim.setRepeatMode(Animation.INFINITE);
                anim.setRepeatCount(Animation.INFINITE);
                imageView.startAnimation(anim);

                break;
            }
            case "NOTES": {
                imageView.setImageResource(R.drawable.ic_notes);
                v.setOnClickListener(v14 -> {
                    Intent launchinIntent = new Intent(activity, NoteActivity.class);
                    activity.startActivity(launchinIntent);
                });
                Animation anim = new ScaleAnimation(
                        0.95f, 1f, // Start and end values for the X axis scaling
                        0.95f, 1f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

                anim.setFillAfter(true); // Needed to keep the result of the animation

                anim.setDuration(2000);
                anim.setRepeatMode(Animation.INFINITE);
                anim.setRepeatCount(Animation.INFINITE);
                imageView.startAnimation(anim);

                break;
            }
            case "PROFILE": {
                imageView.setImageResource(R.drawable.ic_profile);
                v.setOnClickListener(v13 -> {
                    Intent launchinIntent = new Intent(activity, ProfileActivity.class);
                    activity.startActivity(launchinIntent);
                });
                Animation anim = new ScaleAnimation(
                        0.95f, 1f, // Start and end values for the X axis scaling
                        0.95f, 1f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling

                anim.setFillAfter(true); // Needed to keep the result of the animation

                anim.setDuration(2000);
                anim.setRepeatMode(Animation.INFINITE);
                anim.setRepeatCount(Animation.INFINITE);
                imageView.startAnimation(anim);
                break;
            }

        }

        textView.setText(names.get(position));
        return v;
    }

    public static class createRequest extends DialogFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Get the layout inflater
            LayoutInflater inflater = getActivity().getLayoutInflater();
            final View v = inflater.inflate(R.layout.pick_period, null);
            final DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);
            final EditText hour = (EditText) v.findViewById(R.id.periodID);
            final Spinner spn = (Spinner) v.findViewById(R.id.spinnerSubject);

            String qu = "SELECT DISTINCT sub FROM NOTES";
            ArrayList<String> subs = new ArrayList<>();
            subs.add("Not Specified");
            Cursor cr = AppBase.handler.execQuery(qu);
            if (cr != null) {
                cr.moveToFirst();
                while (!cr.isAfterLast()) {
                    subs.add(cr.getString(0));
                    Log.d("GridAdapter.class", "Cached " + cr.getString(0));
                    cr.moveToNext();
                }
            } else
                Log.d("GridAdapter.class", "No SUBS" + cr.getString(0));

            ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_dropdown_item, subs);
            assert spn != null;
            spn.setAdapter(adapterSpinner);
            builder.setView(v)
                    // Add action buttons
                    .setPositiveButton("Enter", (dialog, id) -> {

                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth() + 1;
                        int year = datePicker.getYear();
                        String date = year + "-" + month + "-" + day;
                        String subject = spn.getSelectedItem().toString();
                        String qx = "SELECT title FROM NOTES where sub = '" + subject + "'";
                        Cursor cr1 = AppBase.handler.execQuery(qx);
                        StringBuilder subnames = new StringBuilder();
                        if (cr1 != null) {
                            cr1.moveToFirst();
                            while (!cr1.isAfterLast()) {
                                subnames.append(cr1.getString(0)).append("\n");
                                cr1.moveToNext();
                            }
                        }
//                        makeNotification(subnames.toString());

                        Cursor cursor = AppBase.handler.execQuery("SELECT * FROM ATTENDANCE WHERE datex = '" +
                                date + "' AND hour = " + hour.getText() + ";");
                        if (cursor == null || cursor.getCount() == 0) {
                            Intent launchinIntent = new Intent(AppBase.activity, AttendanceActivity.class);
                            launchinIntent.putExtra("DATE", date);
                            launchinIntent.putExtra("PERIOD", hour.getText().toString());
                            AppBase.activity.startActivity(launchinIntent);
                        } else {
                            Toast.makeText(getActivity(), "Period Already Added", Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
            return builder.create();
        }
    }
}
