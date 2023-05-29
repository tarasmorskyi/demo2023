package taras.morskyi.auctions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import taras.morskyi.auctions.databinding.ItemAuctionBinding
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionBidsViewData

class AuctionAdapter(
    private val context: Context?,
) : RecyclerView.Adapter<AuctionViewHolder>() {

    private var items: MutableList<AuctionBidsViewData> = mutableListOf()

    fun setItems(items: List<AuctionBidsViewData>): Boolean {
        val listDidChange = items != this.items

        val diffCallback = DiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
        return listDidChange
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuctionViewHolder {
        return AuctionViewHolder(
            ItemAuctionBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AuctionViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private class DiffCallback(
        private val oldList: List<AuctionBidsViewData>,
        private val newList: List<AuctionBidsViewData>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}

class AuctionViewHolder(
    val binding: ItemAuctionBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AuctionBidsViewData) {
        binding.date.text = item.date.toString()
        binding.total.text = "${item.totalValue}(${item.lotsCount}/${item.allLotsCount})"
        binding.bids.text = "${item.min} - ${item.mean} - ${item.max}"
    }
}