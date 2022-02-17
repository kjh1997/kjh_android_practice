package com.example.final_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.final_app.databinding.ActivityMainBinding
import com.example.final_app.databinding.FragmentBoardModifyBinding

class BoardModifyFragment : Fragment() {

    //바인딩
    lateinit var boardModifyFargmentBinding : FragmentBoardModifyBinding
    val spinner_data = arrayOf("고객센터", "자유게시판")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        boardModifyFargmentBinding = FragmentBoardModifyBinding.inflate(inflater)
        boardModifyFargmentBinding.boardModifyToolbar.title = "게시글 수정"
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, spinner_data) // 스피너 어댑터 세팅
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        boardModifyFargmentBinding.boardModifyType.adapter = spinnerAdapter

        //메뉴 배치
        boardModifyFargmentBinding.boardModifyToolbar.inflateMenu(R.menu.board_modify_menu)
        boardModifyFargmentBinding.boardModifyToolbar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.board_write_menu_camera->{
                    true
                }
                R.id.board_write_menu_gallery->{
                    true
                }
                R.id.board_modify_menu_upload->{
                    val act = activity as BoardMainActivity
                    act.fragmentRemoveBackStack("board_modify")// 스택에서 제거함

                    true

                }
                else -> false
            }
        }

        return boardModifyFargmentBinding.root
    }


}