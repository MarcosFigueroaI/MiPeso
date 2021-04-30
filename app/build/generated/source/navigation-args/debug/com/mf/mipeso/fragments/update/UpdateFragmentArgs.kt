package com.mf.mipeso.fragments.update

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavArgs
import com.mf.mipeso.model.Peso
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class UpdateFragmentArgs(
  public val currentPeso: Peso
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): UpdateFragmentArgs {
      bundle.setClassLoader(UpdateFragmentArgs::class.java.classLoader)
      val __currentPeso : Peso?
      if (bundle.containsKey("currentPeso")) {
        if (Parcelable::class.java.isAssignableFrom(Peso::class.java) ||
            Serializable::class.java.isAssignableFrom(Peso::class.java)) {
          __currentPeso = bundle.get("currentPeso") as Peso?
        } else {
          throw UnsupportedOperationException(Peso::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__currentPeso == null) {
          throw IllegalArgumentException("Argument \"currentPeso\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"currentPeso\" is missing and does not have an android:defaultValue")
      }
      return UpdateFragmentArgs(__currentPeso)
    }
  }
}
