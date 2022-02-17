package com.example.final_app

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_app.databinding.BoardMainRecyclerItemBinding
import com.example.final_app.databinding.FragmentBoardMainBinding
import java.sql.Driver

class BoardMainFragment : Fragment() {

    // 바인딩
    lateinit var boardMainFragmentBinding : FragmentBoardMainBinding

    val boardListData = arrayOf(
        "고객 센터", "자유게시판"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 뷰 바인딩 , -----툴바
        boardMainFragmentBinding = FragmentBoardMainBinding.inflate(inflater)
        boardMainFragmentBinding.boardMainToolbar.title = "게시판이름"

        boardMainFragmentBinding.boardMainToolbar.inflateMenu(R.menu.board_main_menu)
        boardMainFragmentBinding.boardMainToolbar.setOnMenuItemClickListener {

            when(it.itemId){
                R.id.board_main_menu_board_list -> {
                    val boardListBuilder = AlertDialog.Builder(requireContext())
                    boardListBuilder.setTitle("게시판 목록")
                    boardListBuilder.setNegativeButton("취소", null)
                    boardListBuilder.setItems(boardListData, null)
                    boardListBuilder.show()
                    true
                }
                R.id.board_main_menu_write -> {
                    val act = activity as BoardMainActivity
                    act.fragmentController("board_write", true, true)
                    true
                }
                else -> false
            }
        }

        // 리사이클뷰--------------------
        val boardMainRecyclerAdapter = BoardMainRecyclerAdapter()
        boardMainFragmentBinding.boardMainRecycler.adapter = boardMainRecyclerAdapter
        boardMainFragmentBinding.boardMainRecycler.layoutManager = LinearLayoutManager(requireContext())

        // 구분자
        boardMainFragmentBinding.boardMainRecycler.addItemDecoration(DividerItemDecoration(requireContext(),1))

        return boardMainFragmentBinding.root
    }


    inner class BoardMainRecyclerAdapter : RecyclerView.Adapter<BoardMainRecyclerAdapter.ViewHolderClass>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {

            val boardMainRecyclerItemBinding = BoardMainRecyclerItemBinding.inflate(layoutInflater)
            val holder = ViewHolderClass(boardMainRecyclerItemBinding)

            val layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            boardMainRecyclerItemBinding.root.layoutParams = layoutParams
            boardMainRecyclerItemBinding.root.setOnClickListener(holder)

            return holder
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {

        }

        override fun getItemCount(): Int {
            return 10
        }

        inner class ViewHolderClass(boardMainRecyclerItemBinding:BoardMainRecyclerItemBinding)
            : RecyclerView.ViewHolder(boardMainRecyclerItemBinding.root), View.OnClickListener{
            override fun onClick(v: View?) {
                val act = activity as BoardMainActivity
                act.fragmentController("board_read", true, true)
            }
        }
    }
}