package com.dai1678.quest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dai1678.quest.R
import com.dai1678.quest.databinding.ListItemPatientBinding
import com.dai1678.quest.entity.Patient

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Patient>() {

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.username == newItem.username
    }

    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.username == newItem.username
    }
}

class PatientListAdapter : ListAdapter<Patient, PatientItemHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientItemHolder {
        val binding = DataBindingUtil.inflate<ListItemPatientBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_patient,
            parent,
            false
        )

        return PatientItemHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PatientItemHolder, position: Int) {
        val item = getItem(position)
        holder.binding.patientName = "${item.lastName} ${item.firstName}"
    }

}

class PatientItemHolder(val binding: ListItemPatientBinding) : RecyclerView.ViewHolder(binding.root)
