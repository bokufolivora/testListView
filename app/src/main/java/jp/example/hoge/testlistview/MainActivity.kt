package jp.example.hoge.testlistview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


// 一覧表示のテスト
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list1.setOnClickListener {
            val intent = Intent(application, List1Activity::class.java)
            startActivity(intent)
        }
        list2.setOnClickListener {
            val intent = Intent(application, List2Activity::class.java)
            startActivity(intent)
        }
    }
}