package com.cat.eosinfo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cat.eosinfo.repo.model.*
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

class DetailViewModelTest {
    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskRule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var classUnderTest: DetailViewModel

    @Test
    fun testDetailLiveDataObservation() {
        val producerLiveData = this.classUnderTest.producer.testObserver()
        val pSliveData = this.classUnderTest.producerSignature.testObserver()
        val TransactionCountliveData = this.classUnderTest.transactionCount.testObserver()

        this.classUnderTest.producer.value = "Producer name"
        this.classUnderTest.producerSignature.value = "Signature of Producer"
        this.classUnderTest.transactionCount.value = "1000"

        Truth.assert_().that(producerLiveData.observableValues).isEqualTo(arrayListOf("Producer name") as Any)
        Truth.assert_().that(pSliveData.observableValues).isEqualTo(arrayListOf("Signature of Producer") as Any)
        Truth.assert_().that(TransactionCountliveData.observableValues).isEqualTo(arrayListOf("1000") as Any)
    }

    @Test
    fun testLoadBlock() {
        val block = Block("id")
        val transaction = Transaction(
            1, 1, "", "",
            Trx(
                "Id", arrayListOf("Signature"),
                TransactionX(
                    "Date of Expiring",
                    arrayListOf(
                        Action(
                            "Account 1",
                            arrayListOf(Authorization("actor", "1")), Data(
                                "From A",
                                "Memo mooo",
                                "100",
                                "To B"
                            ),
                            "String",
                            "name"
                        )
                    )
                )
            )
        )

        block.transactions = arrayListOf(transaction)

        val blockLiveData = this.classUnderTest.transactionDataLoad.testObserver()
        this.classUnderTest.loadData(block)

        Truth.assert_().that(blockLiveData.observableValues).isEqualTo(arrayListOf(arrayListOf(transaction)) as Any)
    }
}