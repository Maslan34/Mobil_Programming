package com.example.mobil_programming;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment {
    EditText etLoginUN,etLoginPass;
    Button btnLogin;
    TextView tvNewUser;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        etLoginUN=view.findViewById(R.id.et_LoginUN);
        etLoginPass=view.findViewById(R.id.et_LoginPass);
        btnLogin=view.findViewById(R.id.btn_Login);
        tvNewUser=view.findViewById(R.id.tv_NewUser);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=etLoginUN.getText().toString();
                String password=etLoginPass.getText().toString();
                System.out.println(userName);
                System.out.println(password);
                if(!TextUtils.isEmpty(userName)&& !TextUtils.isEmpty(password))
                    if(userName.equals("muharrem") && password.equals("123"))
                    {
                        ListFragment listFragment=new ListFragment();
                        getParentFragmentManager().beginTransaction().replace(R.id.container,listFragment).commit(); // -->Short Way
                        Toast.makeText(getActivity(), "Login successfull", Toast.LENGTH_SHORT).show();

                    }

                    else
                        Toast.makeText(getActivity(), "Username or password is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager manager=getParentFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                RegisterFragment registerFragment=new RegisterFragment();    ---> Long Way
                transaction.replace(R.id.container,registerFragment);
                transaction.commit();                */

                RegisterFragment registerFragment=new RegisterFragment();
                getParentFragmentManager().beginTransaction().replace(R.id.container,registerFragment).commit(); // -->Short Way

            }
        });



        return view;
    }
}