package com.example.culturallis.View.Fragments.DetailsScreen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Queries.GetCourseInfo;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.CourseDetails.CourseDetails;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.R;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Skeletons.SkeletonCourseConcluded;
import com.example.culturallis.View.Skeletons.SkeletonCourseDetails;
import com.example.culturallis.View.Skeletons.SkeletonSuccessModuleComplete;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseDetailsScreenAdquired#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailsScreenAdquired extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseDetailsScreenAdquired() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDetailsScreenAdquired.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDetailsScreenAdquired newInstance(String param1, String param2) {
        CourseDetailsScreenAdquired fragment = new CourseDetailsScreenAdquired();
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

    Usuario currentUser;
    LoadingSettings loadingDialog;

    CourseDetails courseSelected;

    TextView textView;
    TextView txtUser;
    ImageView img;

    ImageView imgUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course_details_screen_adquired, container, false);

        try {
            UserDAO userDAO = new UserDAO(view.getContext());
            currentUser = new Usuario();
            LoginUserEntity user = userDAO.getLogin();
            currentUser.setEmail(user.getEmail());
            loadingDialog = new LoadingSettings(view.getContext());
            loadingDialog.show();
            Bundle b = getArguments();
            new CourseDetailsScreenAdquired.GetCourseDetails().execute(b.getString("idCourse"));
        } catch (Exception e){
            e.printStackTrace();
        }

        textView = view.findViewById(R.id.titleCourse);
        img = view.findViewById(R.id.courseMainImage);
        txtUser = view.findViewById(R.id.useNameTxt);
        CardView cardView = view.findViewById(R.id.cardProf);
        imgUser = view.findViewById(R.id.perfilImage);
        AppCompatButton txtModule1Title = view.findViewById(R.id.modulo1);
        AppCompatButton txtModule2Title = view.findViewById(R.id.modulo2);
        AppCompatButton txtModule3Title = view.findViewById(R.id.modulo3);
        AppCompatButton txtModule4Title = view.findViewById(R.id.modulo4);
        AppCompatButton txtModule5Title = view.findViewById(R.id.modulo5);
        AppCompatButton txtModule6Title = view.findViewById(R.id.modulo6);

        int[] colorBorderSelected = {
                R.drawable.border_image_blue,
                R.drawable.border_image_orange,
                R.drawable.border_image_purple,
        };

        int[] colorAvatarBorder = {
                R.color.blue_course,
                R.color.orange_course,
                R.color.purple_course,
        };

        int[] colorTagCategory = {
                R.drawable.modules_button_blue,
                R.drawable.modules_button_orange,
                R.drawable.modules_button_purple,
        };


        Random random = new Random();
        int randomIdx;
        randomIdx = random.nextInt(colorBorderSelected.length);


        int randomBorderSelected = colorBorderSelected[randomIdx];
        int randomAvatarBorder = colorAvatarBorder[randomIdx];
        int randomTagColor = colorTagCategory[randomIdx];


        img.setBackground(getResources().getDrawable(randomBorderSelected));
        cardView.setCardBackgroundColor(getResources().getColor(randomAvatarBorder));
        txtModule1Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule2Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule3Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule4Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule5Title.setBackground(getResources().getDrawable(randomTagColor));
        txtModule6Title.setBackground(getResources().getDrawable(randomTagColor));

        return view;
    }

    private class GetCourseDetails extends AsyncTask<String, Void, CourseDetails> {
        @Override
        protected CourseDetails doInBackground(String... params) {
            if (params.length == 1) {

                String courseId = params[0];

                try {
                    return new GetCourseInfo().getCoursesDetails(Long.parseLong(courseId));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(CourseDetails courseDetails) {
            if (loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }

            if (courseDetails != null) {
                Bundle bundle = new Bundle();
                bundle.putString("nome", courseDetails.getNome());
                bundle.putString("courseOwner", courseDetails.getCourseOwner());
                bundle.putString("courseOwnerFoto", courseDetails.getCourseOwnerFoto());
                bundle.putString("categoria", courseDetails.getCategoria());
                bundle.putString("descricao", courseDetails.getDescricao());
                bundle.putString("preco", String.valueOf(courseDetails.getPreco()));
                bundle.putString("modulos", new Gson().toJson(courseDetails.getModulos()));

                courseSelected = courseDetails;

                txtUser.setText(courseSelected.getCourseOwner());
                textView.setText(courseSelected.getNome());
                if(!courseSelected.getCourseOwnerFoto().startsWith("http")){
                    byte[] decodedImagePost = Base64.decode(courseSelected.getCourseOwnerFoto(), Base64.DEFAULT);
                    Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0, decodedImagePost.length);

                    Glide.with(getContext())
                            .load(imageBitmapPost)
                            .into(imgUser);

                }else{
                    Picasso.with(getContext()).load(courseSelected.getCourseOwnerFoto()).into(imgUser);
                }
                if(!courseSelected.getUrl_midia().startsWith("http")){
                    byte[] decodedImagePost = Base64.decode(courseSelected.getUrl_midia(), Base64.DEFAULT);
                    Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0, decodedImagePost.length);

                    Glide.with(getContext())
                            .load(imageBitmapPost)
                            .into(img);

                }else{
                    Picasso.with(getContext()).load(courseSelected.getUrl_midia()).into(img);
                }
            }
        }
    }
}