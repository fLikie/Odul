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

class PhrasebookFragment : Fragment() {

    companion object {
        private const val phrasesFile = "phrasebook.json"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val razgovornikRecyclerView = view.findViewById<RecyclerView>(R.id.razgovornik_recycler_view)
        val data = JsonUtil().parseJson(phrasesFile, requireActivity())
        val adapter = WordsAdapter(data.theme.translations)
        razgovornikRecyclerView.layoutManager = LinearLayoutManager(context)
        razgovornikRecyclerView.adapter = adapter
        (activity as MainActivity).fragmentChanged()
    }
}
