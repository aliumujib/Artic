package com.aliumujib.artic.settings.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aliumujib.artic.R
import com.aliumujib.artic.settings.models.EventsObservable
import com.aliumujib.artic.settings.models.StateSubscriber
import com.aliumujib.artic.settings.models.user.UserProfileEditorModelStore
import com.aliumujib.artic.settings.models.user.UserProfileEditorState
import com.aliumujib.artic.settings.editprofile.EditProfileViewEvent.EmailChange
import com.aliumujib.artic.settings.editprofile.EditProfileViewEvent.FullNameChange
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import javax.inject.Inject

class EditProfileFragment : Fragment() {



    companion object {
        fun newInstance() =
            EditProfileFragment()
    }


    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
    }

    private val viewModel: EditProfileViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(EditProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
