package com.example.culturallis.View.Skeletons;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.culturallis.Controller.Mutations.AdquireCourse;
import com.example.culturallis.Controller.Mutations.CreatePost;
import com.example.culturallis.Controller.Queries.GetCourseInfo;
import com.example.culturallis.Controller.SqLite.UserDAO;
import com.example.culturallis.Model.CourseDetails.CourseDetails;
import com.example.culturallis.Model.Entity.LoginUserEntity;
import com.example.culturallis.Model.Usuario.Usuario;
import com.example.culturallis.Model.ModelAppScreens;
import com.example.culturallis.R;
import com.example.culturallis.View.Configuration.MainSettingsScreen;
import com.example.culturallis.View.Fragments.DetailsScreen.CourseDetailsScreenNotAdquired;
import com.example.culturallis.View.Fragments.LoadingSettings;
import com.example.culturallis.View.Navbar.CourseScreen;
import com.example.culturallis.View.Navbar.HomeScreen;
import com.example.culturallis.View.Navbar.TopNavbar;
import com.example.culturallis.View.Post.PostPublication;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.Random;

import okhttp3.Response;

public class SkeletonSelectedItem extends ModelAppScreens {

    LoadingSettings loadingDialog;

    CourseDetails courseSelected;

    TextView txtCategory;

    ImageView imgView;

    TextView txtTitleCourse;

    TextView txtPriceCourse;

    ImageView imgProfile;

    TextView txtDescriptionCourse;

    TextView txtUserNameCourseOwner;

    double price;

    int courseId;

    private UserDAO userDAO = new UserDAO(this);

    private LoginUserEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton_selected_item);

        CourseDetailsScreenNotAdquired fragment = new CourseDetailsScreenNotAdquired();
        TopNavbar topNavbar = new TopNavbar();

        Bundle b = getIntent().getExtras();

        courseId = Integer.parseInt(b.getString("idCourse"));

        try {
            loadingDialog = new LoadingSettings(this);
            loadingDialog.show();
            new SkeletonSelectedItem.GetCourseDetails().execute(b.getString("idCourse"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frameRender, fragment);
        transaction.replace(R.id.navbarBase, topNavbar);

        transaction.commit();

        txtCategory = findViewById(R.id.textCategoryMain);
        imgView = findViewById(R.id.courseImage);
        txtTitleCourse = findViewById(R.id.titleCourse);
        txtPriceCourse = findViewById(R.id.priceCourse);
        txtUserNameCourseOwner = findViewById(R.id.useNameTxt);
        txtDescriptionCourse = findViewById(R.id.descriptionCourse);
        imgProfile = findViewById(R.id.profileImg);
        CardView cardView = findViewById(R.id.cardProf);

        imgView.setImageResource(R.drawable.logo);

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
                R.drawable.tag_category_blue,
                R.drawable.tag_category_orange,
                R.drawable.tag_category_purple,
        };

        Random random = new Random();
        int randomIdx;
        randomIdx = random.nextInt(colorBorderSelected.length);

        int randomBorderSelected = colorBorderSelected[randomIdx];
        int randomAvatarBorder = colorAvatarBorder[randomIdx];
        int randomTagColor = colorTagCategory[randomIdx];

        imgView.setBackground(getResources().getDrawable(randomBorderSelected));
        txtDescriptionCourse.setBackground(getResources().getDrawable(randomTagColor));
        txtCategory.setBackground(getResources().getDrawable(randomTagColor));
        cardView.setCardBackgroundColor(getResources().getColor(randomAvatarBorder));

        txtCategory.setTextColor(getResources().getColor(R.color.white));

        txtCategory.setText("Python");
        txtTitleCourse.setText("Título Chamativo");
        txtPriceCourse.setText("R$89,99");
        txtCategory.setClickable(false);
        txtUserNameCourseOwner.setText("Culturallis");
        txtDescriptionCourse.setText(
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s. Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.");
    }

    public void changeMainSettings(View v) {
        startActivity(new Intent(this, MainSettingsScreen.class));
        back(v);
    }

    public void changeCoursesHome(View v) {
        startActivity(new Intent(this, HomeScreen.class));
        back(v);
    }

    public void adquireCourse(View view) {
        if (price > 0) {
            Intent intent = new Intent(this, SkeletonPaymentCourse.class);
            Bundle bundle = new Bundle();
            bundle.putDouble("preco", price);
            bundle.putInt("curso", courseId);
            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            try {
                loadingDialog = new LoadingSettings(SkeletonSelectedItem.this);
                loadingDialog.show();
                new AdquireCourses().execute(String.valueOf(courseId), user.getEmail());
                back(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private class AdquireCourses extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (params.length != 2) {
                return false;
            }

            String courseId = params[0];
            String email = params[1];

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
            if (isFinishing() || isDestroyed()) {
                return;
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }

                    if (success) {
                        Toast.makeText(SkeletonSelectedItem.this, "Curso adquirido!", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(SkeletonSelectedItem.this, CourseScreen.class));
                } else {
                        startActivity(new Intent(SkeletonSelectedItem.this, SkeletonBlank.class));
                        Toast.makeText(SkeletonSelectedItem.this, "Ocorreu um erro ao adquirir o curso", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

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

                txtUserNameCourseOwner.setText(courseSelected.getCourseOwner());
                txtTitleCourse.setText(courseSelected.getNome());
                if (!courseSelected.getCourseOwnerFoto().startsWith("http")) {
                    byte[] decodedImagePost = Base64.decode(courseSelected.getCourseOwnerFoto(), Base64.DEFAULT);
                    Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0,
                            decodedImagePost.length);

                    Glide.with(SkeletonSelectedItem.this)
                            .load(imageBitmapPost)
                            .into(imgProfile);

                } else {
                    Picasso.get().load(courseSelected.getCourseOwnerFoto()).into(imgProfile);
                }

                price = courseSelected.getPreco();

                txtCategory.setText(courseSelected.getCategoria());
                txtPriceCourse.setText("R$" + String.valueOf(courseSelected.getPreco()));
                txtDescriptionCourse.setText(courseSelected.getDescricao());

                if (!courseSelected.getUrl_midia().startsWith("http")) {
                    byte[] decodedImagePost = Base64.decode(courseSelected.getUrl_midia(), Base64.DEFAULT);
                    Bitmap imageBitmapPost = BitmapFactory.decodeByteArray(decodedImagePost, 0,
                            decodedImagePost.length);

                    Glide.with(SkeletonSelectedItem.this)
                            .load(imageBitmapPost)
                            .into(imgView);

                } else {
                    Picasso.get().load(courseSelected.getUrl_midia()).into(imgView);
                }
            }
        }
    }
}