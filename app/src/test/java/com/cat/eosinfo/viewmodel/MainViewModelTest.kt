package com.cat.eosinfo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cat.eosinfo.repo.model.Block
import com.cat.eosinfo.testObserver
import com.google.common.truth.Truth
import io.reactivex.Scheduler
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import org.junit.BeforeClass
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


class MainViewModelTest {

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var classUnderTest: MainViewModel

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUpRxSchedulers() {
            val immediate = object : Scheduler() {
                override fun scheduleDirect(@NonNull run: Runnable, delay: Long, @NonNull unit: TimeUnit): Disposable {
                    // this prevents StackOverflowErrors when scheduling with a delay
                    return super.scheduleDirect(run, 0, unit)
                }

                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
                }
            }

            RxJavaPlugins.setInitIoSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { _ -> immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { _ -> immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> immediate }
        }
    }

    @Test
    fun testBlockLiveDataObservation() {
        val liveDataUnderTest = this.classUnderTest.newBlockLiveData.testObserver()
        Truth.assert_().that(liveDataUnderTest.observableValues).isEqualTo(emptyList<Block>())
    }
}