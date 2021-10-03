package com.food_dev.auth.login.ui

import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.food_dev.auth.activity.ui.AuthActivity
import com.food_dev.auth.databinding.FragmentLoginBinding
import com.food_dev.auth.login.viewmodel.LoginViewModel
import com.food_dev.utils.base.BaseFragment
import com.food_dev.utils.base.navigation.NavigationCommand
import com.food_dev.utils.ext.event.EventObserver
import com.food_dev.utils.ext.textview.clearInputEditText
import com.food_dev.utils.ext.textview.getStringTextFromTextInput
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import com.food_dev.auth.BR
import com.food_dev.utils.base.navigation.startMainActivity

@AndroidEntryPoint
@FragmentScoped
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    @FragmentScoped
    override val viewModel: LoginViewModel by viewModels()
    override val binding: FragmentLoginBinding by lazy { FragmentLoginBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel

    override fun initOnClick() {
        addPasswordOnTextChange()
        binding.viewLoginButton.setOnClickListener {
            val email    = getStringTextFromTextInput(binding.viewEmailTextInput.editText)
            val password = getStringTextFromTextInput(binding.viewPasswordTextInput.editText)
            viewModel.loginMerchant(email, password)
        }

        binding.viewRegisterButton.setOnClickListener {
            navigate(NavigationCommand.To(LoginFragmentDirections.toRegister()))
        }
    }

    private fun addPasswordOnTextChange(){
        binding.viewPasswordTextInput.editText?.doOnTextChanged { text, _, _, _ ->
            val textLength = text?.length
            textLength?.let { length ->
                binding.viewLoginButton.isEnabled = length >= 6
            }
        }
    }

    override fun setupObserver() {
        val activity = requireActivity() as AuthActivity
        viewModel.loginDataValue.observe(viewLifecycleOwner, EventObserver{ isResultOk ->
            if (isResultOk){
                clearInputEditText(binding.viewEmailTextInput.editText)
                clearInputEditText(binding.viewEmailTextInput.editText)
                activity.startMainActivity(activity)
            } else {
                clearInputEditText(binding.viewEmailTextInput.editText)
                clearInputEditText(binding.viewEmailTextInput.editText)
            }
        })
    }

}