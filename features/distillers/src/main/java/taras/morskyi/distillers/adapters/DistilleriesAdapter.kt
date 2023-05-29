package taras.morskyi.distillers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import taras.morskyi.distillers.databinding.ItemDistilleryBinding
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryViewData

class DistilleriesAdapter(
    private val context: Context?,
    private val onClick: (DistilleryViewData) -> Unit
) : RecyclerView.Adapter<DistilleryViewHolder>() {

    private var items: MutableList<DistilleryViewData> = mutableListOf()

    fun setItems(items: List<DistilleryViewData>): Boolean {
        val listDidChange = items != this.items

        val diffCallback = DiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
        return listDidChange
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistilleryViewHolder {
        return DistilleryViewHolder(
            ItemDistilleryBinding.inflate(
                LayoutInflater.from(context), parent, false
            ), onClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DistilleryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private class DiffCallback(
        private val oldList: List<DistilleryViewData>,
        private val newList: List<DistilleryViewData>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].slug == newList[newItemPosition].slug

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}

class DistilleryViewHolder(
    val binding: ItemDistilleryBinding,
    val onClick: (DistilleryViewData) -> Unit
) : ViewHolder(binding.root) {
    fun bind(item: DistilleryViewData) {
        binding.title.text = "${item.name}(${item.whiskies})"
        binding.country.text = item.country
        binding.ranking.text = "${item.rating}(${item.votes})"
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}