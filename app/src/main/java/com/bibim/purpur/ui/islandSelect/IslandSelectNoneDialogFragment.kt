package com.bibim.purpur.ui.islandSelect

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bibim.purpur.R
import com.bibim.purpur.onlyOneClickListener
import kotlinx.android.synthetic.main.dialog_name_edit.*

class IslandSelectNoneDialogFragment : DialogFragment(){

    lateinit var listener : OnDialogDismissedListener

    fun setOnDialogDismissedListener(listener: OnDialogDismissedListener) {
        this.listener = listener
    }

    interface OnDialogDismissedListener {
        fun onDialogDismissed()
    }

    override fun dismiss() {
        listener.onDialogDismissed()
        super.dismiss()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_island_slect_none, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

}