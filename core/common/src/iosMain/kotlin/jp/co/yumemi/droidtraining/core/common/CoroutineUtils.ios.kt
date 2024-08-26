package jp.co.yumemi.droidtraining.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual val Dispatchers.IO: CoroutineDispatcher
    get() = Dispatchers.IO
