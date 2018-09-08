package jp.example.hoge.testlistview

import java.lang.Math.abs

// 一覧サンプル用のデータ
class ClsListData {
    private var itemCount = 10
    private val dummyList : ArrayList<String> = ArrayList()
    init {
        dummyList.add("サンプル 001")
        dummyList.add("サンプル 002")
        dummyList.add("サンプル 003")
        dummyList.add("サンプル 004")
        dummyList.add("サンプル 005")
        dummyList.add("サンプル 006")
        dummyList.add("サンプル 007")
        dummyList.add("サンプル 008")
        dummyList.add("サンプル 009")
        dummyList.add("サンプル 010")
    }

    //
    val size : Int
        get() {
            return dummyList.size
        }
    //
    val listData : ArrayList<String>
        get() {
            return dummyList
        }
    //　追加
    fun putItem( ) {
        itemCount++
        val itemData : String = String.format( "サンプル %03d",itemCount)
        dummyList.add(itemData)
    }
    // 取得
    fun getItem( Idx:Int) : String {
        return dummyList[Idx]
    }
    // 削除
    fun deleteItem(Idx:Int) {
        dummyList.removeAt(Idx)
    }
    // 移動
    fun moveItem(formIdx:Int, toIdx:Int) {
        when {
            1 == abs(formIdx-toIdx) -> {
                // ほぼこれで処理
                exchangeItem(formIdx, toIdx)
            }
            formIdx < toIdx -> {
                // 上から下へ
                val tmp = dummyList[formIdx]
                val ePosition = toIdx-1
                for(i:Int in formIdx..ePosition) {
                    exchangeItem(i,i+1)
                }
                dummyList[toIdx] = tmp
            }
            else -> {
                // 下から上へ
                val tmp = dummyList[toIdx]
                val ePosition = formIdx+1
                for(i:Int in toIdx..ePosition) {
                    exchangeItem(i,i-1)
                }
                dummyList[toIdx] = tmp
            }
        }
    }
    //　入れ替え
    private fun exchangeItem(formIdx:Int, toIdx:Int) {
        val tmp : String    = dummyList[toIdx]
        dummyList[toIdx]   = dummyList[formIdx]
        dummyList[formIdx] = tmp
    }
}