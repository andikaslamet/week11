package com.example.mvvm_week4_160421085.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_week4_160421085.databinding.StudentListItemBinding
import com.example.mvvm_week4_160421085.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),ButtonDetailClickListener
{
    class StudentViewHolder(var binding: StudentListItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return StudentViewHolder(binding)
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentFragmentDirections.actionStudentDetailFragment()
        Navigation.findNavController(v).navigate(action)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this
        /*
        holder.binding.btnDetail.setOnClickListener {
            val studentId = studentList[position].id
            val action = StudentFragmentDirections.actionStudentDetailFragment(studentId.toString())
            Navigation.findNavController(it).navigate(action)
        }
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(studentList[position].photoUrl)
            .into(holder.binding.imageView3, object: Callback {
                override fun onSuccess() {
                    holder.binding.progressBar2.visibility = View.INVISIBLE
                    holder.binding.imageView3.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("picasso_error", e.toString())
                }
            })
        */
    }
    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }


}

