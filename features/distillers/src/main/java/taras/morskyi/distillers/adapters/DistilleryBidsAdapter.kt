package taras.morskyi.distillers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import taras.morskyi.distillers.databinding.ItemDistilleryBidBinding
import taras.morskyi.distillers_kmm.data.models.viewdata.DistilleryBidViewData

class DistilleryBidsAdapter(
    private val context: Context?
) : RecyclerView.Adapter<DistilleryBidViewHolder>() {

    private var items: MutableList<DistilleryBidViewData> = mutableListOf()

    fun setItems(items: List<DistilleryBidViewData>): Boolean {
        val listDidChange = items != this.items

        val diffCallback = DiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
        return listDidChange
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DistilleryBidViewHolder {
        return DistilleryBidViewHolder(
            ItemDistilleryBidBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: DistilleryBidViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private class DiffCallback(
        private val oldList: List<DistilleryBidViewData>,
        private val newList: List<DistilleryBidViewData>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}

class DistilleryBidViewHolder(
    val binding: ItemDistilleryBidBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: DistilleryBidViewData) {
        binding.date.text = item.date.toString()
        binding.total.text = "${item.tradingVolume}(${item.lotsCount})"
        binding.bids.text = "${item.bidMin} - ${item.bidMean} - ${item.bidMax}"
    }
}