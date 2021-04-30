package com.mf.mipeso.fragments.list

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.mf.mipeso.R
import com.mf.mipeso.model.Peso
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class ListFragmentDirections private constructor() {
  private data class ActionListFragmentToUpdateFragment(
    public val currentPeso: Peso
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_listFragment_to_updateFragment

    @Suppress("CAST_NEVER_SUCCEEDS")
    public override fun getArguments(): Bundle {
      val result = Bundle()
      if (Parcelable::class.java.isAssignableFrom(Peso::class.java)) {
        result.putParcelable("currentPeso", this.currentPeso as Parcelable)
      } else if (Serializable::class.java.isAssignableFrom(Peso::class.java)) {
        result.putSerializable("currentPeso", this.currentPeso as Serializable)
      } else {
        throw UnsupportedOperationException(Peso::class.java.name +
            " must implement Parcelable or Serializable or must be an Enum.")
      }
      return result
    }
  }

  public companion object {
    public fun actionListFragmentToAddFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_listFragment_to_addFragment)

    public fun actionListFragmentToUpdateFragment(currentPeso: Peso): NavDirections =
        ActionListFragmentToUpdateFragment(currentPeso)
  }
}
