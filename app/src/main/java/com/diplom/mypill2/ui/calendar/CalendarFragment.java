package com.diplom.mypill2.ui.calendar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.diplom.mypill2.R;
import com.diplom.mypill2.databinding.FragmentCalendarBinding;

import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private @NonNull FragmentCalendarBinding binding;
    private TextView textViewFirstDate;
    private TextView textViewSecondDate;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        textViewFirstDate = view.findViewById(R.id.text_view_first_date);
        textViewSecondDate = view.findViewById(R.id.text_view_second_date);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        textViewFirstDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {
                            month += 1;
                            String date = day + "/" + month + "/" + year;
                            textViewFirstDate.setText(date);
                        }
                    }, year, month, day);
                datePickerDialog.show();
            }
        });

        textViewSecondDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {
                                month += 1;
                                String date = day + "/" + month + "/" + year;
                                textViewSecondDate.setText(date);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}