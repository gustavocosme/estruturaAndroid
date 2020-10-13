package com.gustavocosme.atualizacao.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gustavocosme.atualizacao.R
import com.gustavocosme.atualizacao.model.data.DataEventsData
import kotlinx.android.synthetic.main.list_item.view.*


class ListAdp(private val dados:List<DataEventsData>):RecyclerView.Adapter<ListAdp.Holder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false);
        return Holder(v);

    }

    override fun getItemCount() = dados.count();

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.itemView.container.setOnClickListener {

            dados.get(position).name = "OK"
            notifyDataSetChanged();

        }

        holder.set(dados.get(position));

    }

    class Holder(itemView: View) :RecyclerView.ViewHolder(itemView)
    {

        private val txtTitle = itemView.titles;
        private val btnContainer = itemView.container;

        fun set(data:DataEventsData)
        {
            txtTitle.text = data.name;

        }


    }//END CLASS


}//END CLASS