package com.example.mvvm_week4_160421085.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.mvvm_week4_160421085.R
import com.example.mvvm_week4_160421085.databinding.FragmentStudentDetailBinding
import com.example.mvvm_week4_160421085.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private val args: StudentFragmentDirections by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onButtonDetailClick(v: View) {
        viewModel.studentLD.value?.let { student ->
            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "five seconds")
                    MainActivity.showNotification(
                        student.name.toString(),
                        "A new notification created",
                        R.drawable.baseline_person_24
                    )
                }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.student = studentList[position]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listener = this

        val studentId = args.studentId
        viewModel.fetch(studentId)

        /*
        viewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            binding.txtID.setText(student.id)
            binding.txtName.setText(student.name)
            binding.txtBod.setText(student.bod)
            binding.txtPhone.setText(student.phone)
            binding.txtUrl.setText(student.photoUrl)


            Picasso.get()
                .load(student.photoUrl)
                .into(binding.imageView, object : Callback {
                    override fun onSuccess() {
                        Log.d("Picasso Succes", "Succes")
                    }

                    override fun onError(e: Exception?) {
                        Log.e("Picasso Error", "Error loading image: ${e?.localizedMessage}")
                    }
                })
        })


        binding.btnUpdate.setOnClickListener {
            observeViewModel()
        }
        */
    }

    /*
    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it
            binding.btnUpdate?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(
                            student.name.toString(),
                            "A new notification created",
                            R.drawable.baseline_person_24
                        )
                    }
            }
        })
    }
    */
}