package com.example.class3demo2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;


public class StudentDetailsFragment extends Fragment {
//    private static final String ARG_STUDENT_ID = "ARG_STUDENT_ID";
//
//    private String studentId;
//
//    public StudentDetailsFragment() {
//    }
//
//    public static StudentDetailsFragment newInstance(String studentId) {
//        StudentDetailsFragment fragment = new StudentDetailsFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_STUDENT_ID, studentId);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            studentId = getArguments().getString(ARG_STUDENT_ID);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_details, container, false);
        String stId = StudentDetailsFragmentArgs.fromBundle(getArguments()).getStudentId(); // need to get the argument for the student id
        Student student = Model.instance.getStudentById(stId);

        TextView nameTv = view.findViewById(R.id.details_name_tv);
        TextView idTv = view.findViewById(R.id.details_id_tv);

        nameTv.setText(student.getName());
        idTv.setText(student.getId());
        Button backBtn = view.findViewById(R.id.details_back_btn); // going one screen back
        backBtn.setOnClickListener((v)->{
            Navigation.findNavController(v).navigateUp();
        });
        return view;
    }
}