package app.igarashi.igaryo.recruitingapp.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import app.igarashi.igaryo.recruitingapp.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val sv = root.findViewById<SearchView>(R.id.searchView)
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //hideKeyboard()
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
        })
        return root
    }
    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val manager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}
