package com.example.proagro.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proagro.databinding.NotificationBottomSheetBinding

class NotiBtmShtAdapter(
    private val notiDesc: List<String>,
    private val notiImages: List<Int>
) : RecyclerView.Adapter<NotiBtmShtAdapter.NotiBtmShtViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotiBtmShtViewHolder {
        val binding=NotificationBottomSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotiBtmShtViewHolder(binding)
    }



    override fun onBindViewHolder(holder: NotiBtmShtViewHolder, position: Int) {
        holder.bind(notiDesc[position],notiImages[position])
    }

    override fun getItemCount(): Int =notiDesc.size

    class NotiBtmShtViewHolder(private val binding: NotificationBottomSheetBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(disc: String, image: Int) {

            binding.notiBottomSheetText.text=disc
            binding.notiBottomSheetImg.setImageResource(image)

        }

    }


}