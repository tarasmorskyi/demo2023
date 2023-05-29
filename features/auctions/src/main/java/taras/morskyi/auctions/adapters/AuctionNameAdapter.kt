package taras.morskyi.auctions.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import taras.morskyi.auctions.databinding.ItemAuctionNameBinding
import taras.morskyi.auctions_kmm.data.models.viewdata.AuctionNameViewData

class AuctionNameAdapter(
    private val context: Context?,
    private val onClick: (AuctionNameViewData) -> Unit
) : RecyclerView.Adapter<AuctionNameViewHolder>() {

    private var items: MutableList<AuctionNameViewData> = mutableListOf()

    fun setItems(items: List<AuctionNameViewData>): Boolean {
        val listDidChange = items != this.items

        val diffCallback = DiffCallback(this.items, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
        return listDidChange
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuctionNameViewHolder {
        return AuctionNameViewHolder(
            ItemAuctionNameBinding.inflate(
                LayoutInflater.from(context), parent, false
            ), onClick
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AuctionNameViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private class DiffCallback(
        private val oldList: List<AuctionNameViewData>,
        private val newList: List<AuctionNameViewData>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].slug == newList[newItemPosition].slug

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] == newList[newItemPosition]

    }
}

class AuctionNameViewHolder(
    val binding: ItemAuctionNameBinding,
    val onClick: (AuctionNameViewData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: AuctionNameViewData) {
        binding.title.text = "${item.name}"
        binding.root.setOnClickListener {
            onClick(item)
        }
    }
}