package eu.tutorials.a7minuteworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eu.tutorials.a7minuteworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemExerciseStatusBinding): RecyclerView.ViewHolder(binding.root) {
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemExerciseStatusBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Exercise = items[position]
        holder.tvItem.text = model.getID().toString()
    }
}