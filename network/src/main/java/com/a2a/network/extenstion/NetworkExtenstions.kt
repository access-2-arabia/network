package com.a2a.network.extenstion

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


val parentJob = Job()

val coroutineContext: CoroutineContext
    get() = parentJob + Dispatchers.Main

val scope = CoroutineScope(coroutineContext)

