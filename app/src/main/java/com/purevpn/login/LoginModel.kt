package com.purevpn.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.purevpn.BR


class LoginModel : BaseObservable() {

    var userName: String? = null
        @Bindable
        get
        set(value) {
            notifyPropertyChanged(BR.userName)
            field = value
        }


}