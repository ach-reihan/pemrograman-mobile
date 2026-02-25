package com.droidlens

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View

class HistoryFragment : Fragment(R.layout.fragment_history) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.historyList)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
        recyclerView.adapter = HistoryAdapter(buildHistoryItems())
    }

    private fun buildHistoryItems(): List<HistoryItem> {
        return listOf(
            HistoryItem(
                getString(R.string.history_2003_title),
                getString(R.string.history_2003_body)
            ),
            HistoryItem(
                getString(R.string.history_2005_title),
                getString(R.string.history_2005_body)
            ),
            HistoryItem(
                getString(R.string.history_2007_title),
                getString(R.string.history_2007_body)
            ),
            HistoryItem(
                getString(R.string.history_2008_title),
                getString(R.string.history_2008_body)
            ),
            HistoryItem(
                getString(R.string.history_2010_title),
                getString(R.string.history_2010_body)
            ),
            HistoryItem(
                getString(R.string.history_2013_title),
                getString(R.string.history_2013_body)
            ),
            HistoryItem(
                getString(R.string.history_2014_title),
                getString(R.string.history_2014_body)
            )
        )
    }
}
