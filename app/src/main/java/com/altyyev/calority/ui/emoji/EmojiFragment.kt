package com.altyyev.calority.ui.emoji

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.altyyev.calority.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vanniktech.emoji.Emoji
import com.vanniktech.emoji.EmojiView
import com.vanniktech.emoji.listeners.OnEmojiBackspaceClickListener
import com.vanniktech.emoji.listeners.OnEmojiClickListener
import timber.log.Timber

class EmojiFragment : BottomSheetDialogFragment(), OnEmojiClickListener,
    OnEmojiBackspaceClickListener {

    lateinit var emojiView: EmojiView


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emojiView = view.findViewById(R.id.emoji_view)
        setUpViews()
    }

    private fun setUpViews() {
        emojiView.setUp(
            rootView = requireView().rootView,
            onEmojiClickListener = this@EmojiFragment,
            onEmojiBackspaceClickListener = this@EmojiFragment,
            editText = null
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        emojiView.tearDown()
    }

    override fun onEmojiClick(emoji: Emoji) {
        Timber.d("Emoji => $emoji")
        setFragmentResult(EMOJI_REQUEST_KEY, bundleOf(EMOJI_BUNDLE_KEY to emoji.unicode))
        findNavController().popBackStack()
    }

    companion object {
        const val EMOJI_REQUEST_KEY = "emoji"
        const val EMOJI_BUNDLE_KEY = "emoji"
    }

    override fun onEmojiBackspaceClick() {
    }

}