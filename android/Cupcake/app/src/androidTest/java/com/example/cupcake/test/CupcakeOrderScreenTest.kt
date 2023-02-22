package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.theme.CupcakeTheme
import com.example.cupcake.R
import com.example.cupcake.data.DataSource.quantityOptions
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"

        // When SelectOptionScreen is loaded
        composeTestRule.setContent {
            CupcakeTheme() {
                SelectOptionScreen(
                    subtotal = subTotal,
                    options = flavours
                )
            }
        }

        // Then all the options are displayed on the screen.
        flavours.forEach() {
            flavour -> composeTestRule.onNodeWithText(flavour).assertIsDisplayed()
        }

        // And then subtotal is displayed correctly.
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                subTotal
            )
        ).assertIsDisplayed()

        // And then the next button is disabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        // When StartOrderScreen is loaded
        composeTestRule.setContent {
            CupcakeTheme() {
                StartOrderScreen(
                    quantityOptions = quantityOptions
                )
            }
        }

        // Then all the options are displayed on the screen.
        quantityOptions.forEach() {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    @Test
    fun summaryScreen_verifyContent() {
        // Fake order ui state
        val fakeOrderUiState = OrderUiState(
            quantity = 1,
            flavor = "Vanilla",
            date = "Wed Feb 22",
            price = "$2",
            pickupOptions = listOf()
        )

        // When SummaryScreen is loaded
        composeTestRule.setContent {
            CupcakeTheme() {
                OrderSummaryScreen(
                    orderUiState = fakeOrderUiState
                )
            }
        }

        // Then the UI is updated correctly.
        composeTestRule.onNodeWithText(fakeOrderUiState.flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(fakeOrderUiState.date).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.subtotal_price,
                fakeOrderUiState.price
            )
        )
    }

    @Test
    fun flavorScreen_verifyContent() {
        // Given list of options
        val flavours = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And sub total
        val subTotal = "$100"

        // When SummaryScreen is loaded
        composeTestRule.setContent {
            CupcakeTheme() {
                SelectOptionScreen(
                    subtotal = subTotal,
                    options = flavours
                )
            }
        }

        // And one option is selected
        composeTestRule.onNodeWithStringId(R.string.vanilla).performClick()

        // Then the next button is enabled
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}