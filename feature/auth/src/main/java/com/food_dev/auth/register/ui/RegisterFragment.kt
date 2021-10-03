package com.food_dev.auth.register.ui

import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.food_dev.auth.BR
import com.food_dev.auth.activity.ui.AuthActivity
import com.food_dev.auth.databinding.FragmentRegisterBinding
import com.food_dev.auth.register.viewmodel.RegisterViewModel
import com.food_dev.utils.base.BaseFragment
import com.food_dev.utils.base.navigation.NavigationCommand
import com.food_dev.utils.base.navigation.startMainActivity
import com.food_dev.utils.ext.event.EventObserver
import com.food_dev.utils.ext.textview.clearInputEditText
import com.food_dev.utils.ext.textview.getStringTextFromTextInput
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped

@AndroidEntryPoint
@FragmentScoped
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    @FragmentScoped
    override val viewModel: RegisterViewModel by viewModels()
    override val binding: FragmentRegisterBinding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    override val bindingVariable: Int = BR.viewModel

    override fun initOnClick() {
        binding.toolbarBackBtn.setOnClickListener {
            navigate(NavigationCommand.Back)
        }

        addConfirmPasswordTextChange()
        binding.viewButtonRegister.setOnClickListener {
            val storeName       = getStringTextFromTextInput(binding.viewMerchantStoreNameTextInput.editText)
            val email           = getStringTextFromTextInput(binding.viewEmailTextInput.editText)
            val merchantPIC     = getStringTextFromTextInput(binding.viewMerchantPicTextInput.editText)
            val password        = getStringTextFromTextInput(binding.viewMerchantPasswordTextInput.editText)
            val confirmPassword = getStringTextFromTextInput(binding.viewMerchantConfirmPasswordTextInput.editText)

            if(password != confirmPassword) viewModel.showSnack.value = "Password And Confirm Password Must Match!"
            viewModel.registerUser(email, storeName, merchantPIC, password)
        }
    }

    private fun addConfirmPasswordTextChange(){
        binding.viewMerchantConfirmPasswordTextInput.editText?.doOnTextChanged { text, _, _, _ ->
            val textLength = text?.length
            textLength?.let { length ->
                val password        = getStringTextFromTextInput(binding.viewMerchantPasswordTextInput.editText)
                val confirmPassword = getStringTextFromTextInput(binding.viewMerchantConfirmPasswordTextInput.editText)
                binding.viewButtonRegister.apply {
                    isEnabled = password == confirmPassword && password.length >= 8 && confirmPassword.length >= 8
                }
            }
        }
    }

    override fun setupObserver() {
        val activity = requireActivity() as AuthActivity
        viewModel.registerDataValue.observe(viewLifecycleOwner, EventObserver{ isResultOk ->
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