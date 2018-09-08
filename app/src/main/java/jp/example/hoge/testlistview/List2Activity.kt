package jp.example.hoge.testlistview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import android.view.View
import kotlinx.android.synthetic.main.activity_list2.*

// 移動、スワイプができる一覧表示
class List2Activity : AppCompatActivity() {
    // データ用意
    private val mSampleData = ClsListData()
    // リスト用アダプター
    private lateinit var adapter : RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)

        // リストへデータを設定
        adapter = RecyclerAdapter(this, mSampleData)
        listView2.adapter = adapter

        // クリック
        adapter.setOnItemClickListener(object : RecyclerAdapter.onItemClickListener {
            override fun onClick( name: String) {
                // クリックされた行のデータを表示
                Toast.makeText(this@List2Activity, name, Toast.LENGTH_SHORT).show()
            }
        })
        //　
        val mIth = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
                // 反応する動作の指定
                return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
            }
            // 上下に動かした場合　アイテムの位置を変更
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPos: Int = viewHolder.adapterPosition
                val toPos: Int = target.adapterPosition
                // 表示位置を移動
                listView2.adapter.notifyItemMoved(fromPos, toPos)
                // データを移動
                mSampleData.moveItem(fromPos,toPos)
                return true
            }
            // スワイプされた場合　項目を消去
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val fromPos: Int = viewHolder.adapterPosition
                // 表示データから削除
                listView2.adapter.notifyItemRemoved(fromPos)
                // 一覧データから削除
                mSampleData.deleteItem(fromPos)
            }
        })
        mIth.attachToRecyclerView(listView2)
        listView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // 追加ボタン
        btnAdd2.setOnClickListener {
            adapter.addItem()
            listView2.adapter = adapter
        }
    }
}