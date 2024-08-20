package jp.co.yumemi.droidtraining.core.common

import android.util.Log
import kotlinx.coroutines.CancellationException

suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (throwable: Throwable) {
    Log.w("SuspendRunCatching", "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result", throwable)
    Result.failure(throwable)
}
