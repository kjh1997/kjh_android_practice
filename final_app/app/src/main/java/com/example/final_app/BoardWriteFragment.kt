package com.example.final_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.final_app.databinding.FragmentBoardReadBinding
import com.example.final_app.databinding.FragmentBoardWriteBinding


class BoardWriteFragment : Fragment() {

    // 프래그먼트 바인딩
    lateinit var boardWriteFragmentBinding : FragmentBoardWriteBinding

    val spinner_date = arrayOf("고객센터", "자유게시판")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        boardWriteFragmentBinding = FragmentBoardWriteBinding.inflate(inflater) // 맨 위에서 바인딩할 변수를 만들고 여기서 바인딩을 했음.

        boardWriteFragmentBinding.boardWriteToolbar.title="게시글 작성"

        // 스피너 어댑터
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinner_date)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)    // 어댑터 구성
        boardWriteFragmentBinding.boardWriteType.adapter = spinnerAdapter // 어댑터 적용

        //메뉴 셋팅 / 클릭시 이동함
        boardWriteFragmentBinding.boardWriteToolbar.inflateMenu(R.menu.board_write_menu)
        boardWriteFragmentBinding.boardWriteToolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.board_write_menu_camera ->{
                    true
                }
                R.id.board_write_menu_gallery->{
                    true
                }
                R.id.board_write_menu_upload ->{
                    val act = activity as BoardMainActivity
                    act.fragmentRemoveBackStack("board_write")
                    act.fragmentController("board_read",true,true)
                    true
                }
                else -> false
            }

        }


        return boardWriteFragmentBinding.root
    }


}