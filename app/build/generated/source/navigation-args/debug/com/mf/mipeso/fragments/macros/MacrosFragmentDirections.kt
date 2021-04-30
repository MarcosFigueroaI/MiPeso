package com.mf.mipeso.fragments.macros

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.mf.mipeso.R

public class MacrosFragmentDirections private constructor() {
  public companion object {
    public fun actionMacrosFragmentToListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_macrosFragment_to_listFragment)
  }
}
