package com.example.class3demo2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

import java.util.List;

public class StudentListRvFragment extends Fragment {

    List<Student> data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_students_list,container,false);
        data = Model.instance.getAllStudents();

        RecyclerView list = view.findViewById(R.id.studentlist_rv);
        list.setHasFixedSize(true);

        list.setLayoutManager(new LinearLayoutManager(getContext()));

        MyAdapter adapter = new MyAdapter();
        list.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                Log.d("TAG","row was clicked " + position); //TODO: class 5
//                String id = data.get(position).getId();
//                StudentDetailsFragment frag = StudentDetailsFragment.newInstance(id);
//                FragmentTransaction tran = getParentFragmentManager().beginTransaction();
//                tran.add(R.id.base_frag_container,frag);
//                tran.addToBackStack("");
//                tran.commit();
                String stId = data.get(position).getId();
                Navigation.findNavController(v).navigate(StudentListRvFragmentDirections.actionStudentListRvFragmentToStudentDetailsFragment(stId));
            }
        });
        // for the + Button going to the forward screen
        ImageButton add = view.findViewById(R.id.studentlist_add_btn);
//        add.setOnClickListener((v)->{
//            Navigation.findNavController(v).navigate(R.id.action_studentListRvFragment_to_studentDetailsFragment);
//        });
        add.setOnClickListener(Navigation.createNavigateOnClickListener(StudentDetailsFragmentDirections.actionGlobalAboutFragment()));
        //TODO: MARK
        setHasOptionsMenu(true); // מבחינת אנדרואיד אנדרואיד יודע שלפרגמנט הזה יש menu
        return view;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.listrow_name_tv);
            idTv = itemView.findViewById(R.id.listrow_id_tv);
            cb = itemView.findViewById(R.id.listrow_cb);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(v, pos);
                }
            });
        }
    }

    interface OnItemClickListener{
        void onItemClick(View v, int position);
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

        OnItemClickListener listener;
        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row,parent,false);
            MyViewHolder holder = new MyViewHolder(view,listener);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Student student = data.get(position);
            holder.nameTv.setText(student.getName());
            holder.idTv.setText(student.getId());
            holder.cb.setChecked(student.isFlag());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.student_list_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_add) {
            Log.d("TAG", "Add...");
                    return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }
}