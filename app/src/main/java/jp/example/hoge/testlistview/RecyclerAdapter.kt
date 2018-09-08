package jp.example.hoge.testlistview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.LinearLayout

// RecyclerView用の操作
class RecyclerAdapter(private val context: Context, private val nameList: ClsListData ) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    private var listener: onItemClickListener? = null

    // １行分
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linearLayout: LinearLayout = itemView.findViewById(R.id.linear_layout) as LinearLayout
        val textView: TextView          = itemView.findViewById(R.id.itemTextView) as TextView
    }
    //
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return RecyclerViewHolder(view)
    }
    // クリック時の処理
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val number = nameList.getItem(position)
        holder.textView.text = number
        holder.linearLayout.setOnClickListener { _ ->
            listener!!.onClick( nameList.getItem(holder.adapterPosition))
        }
    }
    // 個数
    override fun getItemCount(): Int {
        return nameList.size
    }
    // 追加
    fun addItem() {
        nameList.putItem()
    }

    // クリック リスナーの設定
    fun setOnItemClickListener(listener: onItemClickListener) {
        this.listener = listener
    }
    interface onItemClickListener {
        fun onClick(name: String)
    }
}