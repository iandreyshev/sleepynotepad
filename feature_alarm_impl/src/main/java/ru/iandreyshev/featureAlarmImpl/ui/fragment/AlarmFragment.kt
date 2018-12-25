package ru.iandreyshev.featureAlarmImpl.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.iandreyshev.coreui.ui.fragment.BaseFragment
import ru.iandreyshev.featureAlarmImpl.R

class AlarmFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_alarms_list, container, false)

}
