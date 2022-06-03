package com.diplom.mypill2.ui.enter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.diplom.mypill2.R;
import com.diplom.mypill2.jdbc.UserJdbc;

public class RegistrationFragment extends Fragment {
    private EditText editTextEmailRegistration;
    private EditText editTextPasswordRegistration;
    private Button buttonRegistrationRegistration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        editTextEmailRegistration = view.findViewById(R.id.edit_text_email_registration);
        editTextPasswordRegistration = view.findViewById(R.id.edit_text_password_registration);
        buttonRegistrationRegistration = view.findViewById(R.id.button_registration_registration);
        UserJdbc userJdbc = new UserJdbc(getActivity().getBaseContext());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmailRegistration.getText().toString();
                String password = editTextPasswordRegistration.getText().toString();

                userJdbc.insert(email, password);
                editTextEmailRegistration.setText("");
                editTextPasswordRegistration.setText("");
                Toast.makeText(getActivity().getBaseContext(), "reg done", Toast.LENGTH_SHORT);
            }
        };

        buttonRegistrationRegistration.setOnClickListener(listener);

        return view;
    }
}