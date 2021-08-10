package com.example.myopinion.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.myopinion.R
import com.example.myopinion.adapters.CommentItemAdapter
import com.example.myopinion.databinding.FragmentCommentsBinding
import com.example.myopinion.helpers.PopupMenuShower
import com.example.myopinion.models.Comment
import com.example.myopinion.netReq.CommentDataRequest
import com.example.myopinion.netReq.CommentDataRequestProvider
import com.example.myopinion.tools.CommentsChecker.Companion.check
import com.example.myopinion.viewmodel.CommentsViewModel
import com.example.myopinion.viewmodel.ViewModelFactory

class CommentsFragment : Fragment() {

    private lateinit var binding: FragmentCommentsBinding
    private lateinit var commentItemAdapter: CommentItemAdapter
    private lateinit var list: ArrayList<Comment>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCommentsBinding.inflate(inflater, container, false)
        list = ArrayList()
        val layoutManager = LinearLayoutManager(requireContext(), VERTICAL, false)
        binding.rv.layoutManager = layoutManager

        commentItemAdapter =
            CommentItemAdapter(list, object :  CommentItemAdapter.OnItemClickListener {
                override fun onClick(comment: Comment, position: Int) {
                    val popupMenuShower = PopupMenuShower(requireContext())
                    popupMenuShower.showPopupMenu(layoutManager.findViewByPosition(position)!!)
                }
            },requireContext())
        binding.rv.adapter = commentItemAdapter

        val commentsViewModel =
            ViewModelProvider(this, ViewModelFactory(this))[CommentsViewModel::class.java]

        commentsViewModel.getCommentsLiveData().observe(requireActivity(), {
            list.clear()
            list.addAll(it)
            commentItemAdapter.notifyDataSetChanged()
            binding.noCommentsTv.check(list)
        })

        binding.sendCommentBtn.setOnClickListener {
            commentsViewModel.sendComment(binding.inputCommentText)
            commentItemAdapter.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu, menu)
    }
}