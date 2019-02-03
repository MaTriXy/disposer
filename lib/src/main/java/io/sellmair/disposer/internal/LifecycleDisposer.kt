package io.sellmair.disposer.internal

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.GenericLifecycleObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.sellmair.disposer.Disposer


/*
################################################################################################
INTERNAL API
################################################################################################
*/

internal class LifecycleDisposer(
    lifecycle: Lifecycle,
    vararg events: Lifecycle.Event) : Disposer by LockedDisposer() {

    private val observer: LifecycleObserver = GenericLifecycleObserver { _, event ->
        if (events.contains(event)) {
            dispose()
        }
    }

    init {
        lifecycle.addObserver(observer)
    }
}