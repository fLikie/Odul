package com.smartbooks.odul.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smartbooks.odul.MainActivity
import com.smartbooks.odul.R
import com.smartbooks.odul.utils.JsonUtil

class VocabularyFragment : Fragment() {

    companion object {
        private const val vocabularyFile = "vocabulary.json"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeRecyclerView = view.findViewById<RecyclerView>(R.id.home_recycler_view)
        val data = JsonUtil().parseJson(vocabularyFile, requireActivity())
        val adapter = WordsAdapter(data.theme.translations)
        homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeRecyclerView.adapter = adapter
        (activity as MainActivity).fragmentChanged()
    }
}