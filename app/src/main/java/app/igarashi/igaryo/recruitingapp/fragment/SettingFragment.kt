package app.igarashi.igaryo.recruitingapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.igarashi.igaryo.recruitingapp.R

class SettingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_setting, container, false)
        val textView: TextView = root.findViewById(R.id.text_setting)
        textView.text = "setting"
        return root
    }
}