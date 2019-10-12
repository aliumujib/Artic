package com.aliumujib.artic.ui.main.settings.editprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aliumujib.artic.R
import com.aliumujib.artic.models.EventsObservable
import com.aliumujib.artic.presentation.EditProfileViewModel
import com.aliumujib.artic.ui.main.settings.editprofile.EditProfileViewEvent.EmailChange
import com.aliumujib.artic.ui.main.settings.editprofile.EditProfileViewEvent.FullNameChange
import com.jakewharton.rxbinding3.widget.textChanges
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.edit_profile_fragment.*
import javax.inject.Inject

class EditProfileFragment : Fragment(), EventsObservable<EditProfileViewEvent> {

    private val compositeDisposable = CompositeDisposable()

    override fun events(): Observable<EditProfileViewEvent> {
        return Observable.merge(et_full_name.textChanges().map {
            FullNameChange(it.toString())
        }, et_email.textChanges().map {
            EmailChange(it.toString())
        })
    }

    companion object {
        fun newInstance() = EditProfileFragment()
    }

    @Inject
    lateinit var intentFactory: ProfileEditorIntentFactory

    override fun onResume() {
        super.onResume()
        compositeDisposable.addAll(events().subscribe(intentFactory::process))
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    private val viewModel: EditProfileViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(EditProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidSupportInjection.inject(this)
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
