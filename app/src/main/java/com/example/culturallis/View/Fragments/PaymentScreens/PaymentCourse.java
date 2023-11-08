package com.example.culturallis.View.Fragments.PaymentScreens;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.culturallis.Controller.Mutations.AdquireCourse;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Skeletons.SkeletonBlank;
import com.example.culturallis.View.Skeletons.SkeletonSelectedItem;

import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaymentCourse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentCourse extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LoginUserEntity user;
    LoadingSettings loadingDialog;

    public PaymentCourse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PaymentCourse.
     */
    // TODO: Rename and change types and number of parameters
    public static PaymentCourse newInstance(String param1, String param2) {
        PaymentCourse fragment = new PaymentCourse();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_course, container, false);

        UserDAO userDAO = new UserDAO(view.getContext());

        user = userDAO.getLogin();

        ImageView im = view.findViewById(R.id.pixImage);
        TextView txtPrice = view.findViewById(R.id.textView8);
        AppCompatButton btnBuy = view.findViewById(R.id.btnBuyCourse);

        Bundle b = getArguments();
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    loadingDialog = new LoadingSettings(getContext());
                    loadingDialog.show();
                    new AquireCourse().execute(String.valueOf(b.getInt("curso")), user.getEmail());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        txtPrice.setText("R$" + String.format("%.2f", b.getDouble("preco")));
        im.setImageResource(R.drawable.pix_courses);
        return view;
    }

    private class AquireCourse extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 2) {
                return false;
            }

            String courseId = params[0];
            String email= params[1];

            try {
                AdquireCourse mutations = new AdquireCourse();
                Response response = mutations.adquireCourse(Long.parseLong(courseId), email);
                return response.isSuccessful();
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            if (success) {
                Toast.makeText(getContext(), "Curso adquirido!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            } else {
                startActivity(new Intent(getContext(), SkeletonBlank.class));
                Toast.makeText(getContext(), "Ocorreu um erro ao adquirir o curso", Toast.LENGTH_SHORT).show();
            }
        }
    }
}