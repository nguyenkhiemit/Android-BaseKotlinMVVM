package com.newgate.basekotlinmvvm.menu.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.newgate.basekotlinmvvm.R
import com.newgate.basekotlinmvvm.databinding.LayoutItemMenuBinding
import com.newgate.basekotlinmvvm.databinding.LayoutItemTitleMenuBinding
import com.newgate.basekotlinmvvm.menu.model.Menu

/**
 * Created by apple on 9/9/17.
 */
class MenuAdapter(val context: Context, val arrayMenu: ArrayList<Menu>, val itemClick: (Menu) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return arrayMenu.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder? = null
        when(viewType) {
            0 -> {
                var binding: LayoutItemTitleMenuBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.layout_item_title_menu,
                        parent, false)
                holder = TitleMenuViewHolder(binding)
            }

            1 -> {
                var binding: LayoutItemMenuBinding = DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.layout_item_menu,
                        parent, false)
                holder = MenuViewHolder(binding, itemClick)
            }
        }
        return holder!!
    }

    override fun getItemViewType(position: Int): Int {
        return arrayMenu[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        var menu = arrayMenu[position]
        if(holder is MenuViewHolder) {
            holder.bindData(menu)
        } else if(holder is TitleMenuViewHolder) {
            holder.bindData(menu)
        }
    }

    class TitleMenuViewHolder(var binding: LayoutItemTitleMenuBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindData(menu: Menu) {
            binding.menu = menu
        }
    }
}

    class MenuViewHolder(var binding: LayoutItemMenuBinding, val itemClick: (Menu) -> Unit): RecyclerView.ViewHolder(binding.root) {

        fun bindData(menu: Menu) {
            binding.menu = menu
            binding.root.setOnClickListener {
                itemClick(menu)
            }
        }
    }