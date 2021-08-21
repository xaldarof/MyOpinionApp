package com.opinion.myopinion.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.opinion.myopinion.R
import com.opinion.myopinion.adapters.CommentItemAdapter
import com.opinion.myopinion.databinding.FragmentCommentsBinding
import com.opinion.myopinion.helpers.MyTextWatcher
import com.opinion.myopinion.helpers.PopupMenuShower
import com.opinion.myopinion.helpers.TextWatcherHelper
import com.opinion.myopinion.helpers.WriteCommentTextWatcher
import com.opinion.myopinion.models.Comment
import com.opinion.myopinion.tools.CommentsChecker.Companion.check
import com.opinion.myopinion.viewmodel.CommentsViewModel
import com.opinion.myopinion.viewmodel.ViewModelFactory

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
        binding.sendCommentBtn.isEnabled = false

        val writeCommentTextWatcher = WriteCommentTextWatcher(requireContext())
        writeCommentTextWatcher.init(binding.inputCommentText,binding.sendCommentBtn)

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