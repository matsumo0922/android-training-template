package jp.co.yumemi.droidtraining.core.common

import io.github.aakira.napier.Napier
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (throwable: Throwable) {
    Napier.w(throwable) { "Failed to evaluate a suspendRunCatchingBlock. Returning failure Result" }
    Result.failure(throwable)
}
