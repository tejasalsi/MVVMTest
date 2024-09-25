package com.tejas.mvvm.test.presentation.productsList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.tejas.mvvm.test.R
import com.tejas.mvvm.test.base.getOrAwaitValue
import com.tejas.mvvm.test.base.launchFragmentInHiltContainer
import com.tejas.mvvm.test.domain.usecase.ProductsListUseCase
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class ProductsListFragmentTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun clickSearch_ProductListResponseLiveDataGetsNotified(){
        val testViewModel = ProductsFragmentViewModel(ApplicationProvider.getApplicationContext(), mock(
            ProductsListUseCase::class.java))
        launchFragmentInHiltContainer<ProductsListFragment> {
            this.viewModel =  testViewModel
        }

        onView(withId(R.id.ed_txt_maker)).perform(replaceText("Audi"))
        onView(withId(R.id.ed_txt_model)).perform(replaceText("A4"))
        onView(withId(R.id.ed_txt_year)).perform(replaceText("2024"))
        onView(withId(R.id.btn_search)).perform(
                click())
        testViewModel.productListResponse.observeForever {
            assertThat(testViewModel.productListResponse.getOrAwaitValue()).isNotEmpty()
        }
    }

}