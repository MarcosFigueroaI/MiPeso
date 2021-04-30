package com.mf.mipeso.fragments.add

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.mf.mipeso.R

public class AddFragmentDirections private constructor() {
  public companion object {
    public fun actionAddFragmentToListFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_addFragment_to_listFragment)
  }
}
