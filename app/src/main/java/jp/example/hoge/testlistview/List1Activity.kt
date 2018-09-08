package jp.example.hoge.testlistview

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list1.*

// 単純な一覧表示 (ListView)
class List1Activity : AppCompatActivity() {
    // データ準備
    private val mSampleData = ClsListData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list1)

        btnAdd1.setOnClickListener {
            mSampleData.putItem()
            listRedisply()
        }
        // クリック
        listView1.setOnItemClickListener {_, _, position, _ ->
            val selText = mSampleData.getItem(position)
            Toast.makeText(this, "Clicked: $selText", Toast.LENGTH_SHORT).show()
        }
        // 長押し
        listView1.setOnItemLongClickListener { _, _, position, _ ->
            // 確認用ダイアログ表示
            AlertDialog.Builder(this).apply {
                setTitle("削除")
                setMessage(mSampleData.getItem(position))
                setPositiveButton("OK") { _, _ ->
                    // OKをタップしたときの処理
                    mSampleData.deleteItem(position)
                    listRedisply()
                }
                setNegativeButton("Cancel", null)
                show()
            }
            return@setOnItemLongClickListener true
        }
    }
    //
    override fun onResume() {
        super.onResume()
        listRedisply()
    }
    //
    private fun listRedisply() {
        val listAdapter = ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1)
        val data = mSampleData.listData
        for(i:Int in 1..data.size) {
            listAdapter.add(data[i-1])
        }
        listView1.adapter = listAdapter
    }
}