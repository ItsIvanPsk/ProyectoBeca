package com.example.primerproyecto.presentation.features.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.primerproyecto.R
import com.example.primerproyecto.databinding.ListItemBinding

object TaskDiffCallBack : DiffUtil.ItemCallback<TaskEntity>() {
    override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean {
        return oldItem == newItem
    }
}

class DataAdapter(private var fragment : TaskFragment) : ListAdapter<TaskEntity, DataAdapter.TaskViewHolder>(TaskDiffCallBack) {
    var tasks = listOf<TaskEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding, fragment)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task: TaskEntity = tasks[position]
        holder.bind(task, position, fragment)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    inner class TaskViewHolder(private val binding: ListItemBinding, fragment : TaskFragment) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item : TaskEntity, position : Int, fragment: TaskFragment){
            binding.taskLabel.text = item.taskName
            if(item.image){
                binding.taskImage.load("https://picsum.photos/id/$position/50/50") {
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_person_24)
                    transformations(CircleCropTransformation())
                }
            }

            binding.taskBackground.setOnClickListener {
                fragment.showEditDialog(item)
            }
        }
    }
}