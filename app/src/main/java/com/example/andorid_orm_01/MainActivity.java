package com.example.andorid_orm_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.andorid_orm_01.dao.AppDatabase;
import com.example.andorid_orm_01.dao.StudentDAO;
import com.example.andorid_orm_01.models.Student;
import com.example.andorid_orm_01.utils.AppConstants;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGUI();
        initDatabase();
    }

    private void initGUI(){
        this.btn_submit = findViewById(R.id.btn_submit);
        this.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_LONG).show();
                StudentDAO studentDAO = database.getStudentDAO();
                Student std = new Student();
                std.setName("Thang cute");
                std.setDtb(5.0);
                studentDAO.insert(std);

                List<Student> listStudent = studentDAO.getStudents();
                for(int i=0; i < listStudent.size(); i++){
                    Log.d("name", listStudent.get(i).getName());
                }
            }
        });
    }
    private void initDatabase(){
        database = Room.databaseBuilder(this, AppDatabase.class, AppConstants.DATABASE)
                .allowMainThreadQueries()
                .build();
    }

}