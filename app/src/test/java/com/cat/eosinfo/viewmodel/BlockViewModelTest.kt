package com.cat.eosinfo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cat.eosinfo.testObserver
import com.google.common.truth.Truth
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class BlockViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var classUnderTest: BlockItemViewModel

    @Test
    fun testBlockItemLiveDataObservation() {
        val liveDataUnderTest = this.classUnderTest.blockName.testObserver()
        this.classUnderTest.blockName.value = "Block Name"
        Truth.assert_().that(liveDataUnderTest.observableValues).isEqualTo(arrayListOf("Block Name") as Any)
    }
}