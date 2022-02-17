package com.example.final_app

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.final_app.databinding.FragmentBoardReadBinding


class BoardReadFragment : Fragment() {

    // 바인딩
    lateinit var boardReadFragmentBinding : FragmentBoardReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        boardReadFragmentBinding = FragmentBoardReadBinding.inflate(inflater)
        boardReadFragmentBinding.boardReadToolbar.title = "게시글 읽기"

        // 백 버튼 만들고 있음. 자동으로 생김
        val navIcon = requireContext().getDrawable(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        boardReadFragmentBinding.boardReadToolbar.navigationIcon = navIcon

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            boardReadFragmentBinding.boardReadToolbar.navigationIcon?.colorFilter = BlendModeColorFilter(
                Color.parseColor("#FFFFFF"),BlendMode.SRC_ATOP)   // 요거 한줄임
        } else{
           boardReadFragmentBinding.boardReadToolbar.navigationIcon?.setColorFilter(Color.parseColor("#FFFFFF"),PorterDuff.Mode.SRC_ATOP)
        }

        boardReadFragmentBinding.boardReadToolbar.setNavigationOnClickListener{
            val act = activity as BoardMainActivity
            act.fragmentRemoveBackStack("board_read") // boardMainActivity에서 만든 함수를 사용함
        }
        // 메뉴 배치
        boardReadFragmentBinding.boardReadToolbar.inflateMenu(R.menu.board_read_menu)
        boardReadFragmentBinding.boardReadToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.board_read_menu_delete ->{
                    val act =activity as BoardMainActivity
                    act.fragmentRemoveBackStack("board_read")
                    true
                }
                R.id.board_read_menu_modify->{
                    val act = activity as BoardMainActivity
                    act.fragmentController("board_modify",true, true)
                    true
                }
                else->false
            }
        }

        return boardReadFragmentBinding.root
    }

}