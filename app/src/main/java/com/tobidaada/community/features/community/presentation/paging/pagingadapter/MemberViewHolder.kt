package com.tobidaada.community.features.community.presentation.paging.pagingadapter

import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.tobidaada.community.R
import com.tobidaada.community.databinding.ListItemMemberBinding
import com.tobidaada.community.features.community.domain.entities.User

class MemberViewHolder(
    private val binding: ListItemMemberBinding,
    private val onItemClicked: (userId: Int, isLiked: Boolean) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User): Unit = with(binding) {

        profileImage.load(user.pictureUrl) {
            crossfade(true)
        }

        profileName.text = user.firstName
        profileTopic.text = user.topic

        likeBtn.isSelected = user.isLiked

        setupProfileReferenceView(profileReferenceCount, user.referenceCnt)

        setupLanguageView(profileNative, user.natives, R.string.native_label)
        setupLanguageView(profileLearns, user.learns, R.string.learns_label)

        val onClick = { _: View -> onItemClicked(user.id, !user.isLiked) }
        likeBtn.setOnClickListener(onClick)
        root.setOnClickListener(onClick)
    }

    private fun setupProfileReferenceView(textView: TextView, referenceCount: Int) {
        val context = textView.context
        when (referenceCount) {
            0 -> {
                textView.background = ContextCompat.getDrawable(context, R.drawable.background_new)
                textView.text = context.getString(R.string.new_label)
            }
            else -> {
                textView.background = null
                textView.text = referenceCount.toString()
            }
        }
    }

    private fun setupLanguageView(
        textView: TextView,
        languages: List<String>,
        @StringRes text: Int
    ) {
        val context = textView.context

        if (languages.isEmpty()) {
            textView.visibility = View.INVISIBLE
            return
        }

        textView.visibility = View.VISIBLE
        val stringText = context.getString(text, languages.joinToString(", ")).uppercase()
        val spannable = SpannableString(stringText)

        val startIndex = 6
        val endIndex = stringText.length
        spannable.setSpan(
            RelativeSizeSpan(0.8f),
            startIndex,
            endIndex,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        textView.text = spannable
    }
}